package com.example.dadosmeteorologicos.Services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.dadosmeteorologicos.exceptions.CSVInvalidoException;
import com.example.dadosmeteorologicos.exceptions.NomeCSVInvalidoException;
import com.example.dadosmeteorologicos.model.Registro;
import com.example.dadosmeteorologicos.model.VariavelClimatica;

import lombok.Data;

import java.util.HashMap;

@Data
public class CSVResolve {
    private String caminhoCSV;
    private List<String[]> csvPadronizado = new ArrayList<>();
    private boolean automatico = true;
    private boolean nomeInvalido = false;
    private String codigoEstacao = "";
    private String siglaCidade = "";
    private String[] cabecalhoCSV = null;
    private Map<String, Integer> camposAutomatico = new HashMap<>();
    private Map<String, Integer> camposManual = new HashMap<>();
    DateTimeFormatter formatadorDia = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter formatadorHora = DateTimeFormatter.ofPattern("HHmm");


    public CSVResolve(String caminhoCSV) {
        this.caminhoCSV = caminhoCSV;
    }


    public boolean validarCSV() throws CSVInvalidoException{
        inicializarHashMap();
        try{
            lerNomeCSV();
        } catch(NomeCSVInvalidoException e){
            nomeInvalido = true;
        }
        lerCSV();
        return true;
    }

    public void lerCSV() throws CSVInvalidoException{
        try {
            BufferedReader br = new BufferedReader(new FileReader(caminhoCSV));
    
            String linha;
            int linhaAtual = 0;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";", -1);
                for(int i = 0; i < dados.length; i++){
                    dados[i] = dados[i].replace("\"", "");
                    dados[i] = dados[i].replace(",", ".");
                    dados[i] = dados[i].replace(",", ".");
                    dados[i] = dados[i].replace("ï»¿", "");
                    dados[i] = dados[i].trim().replace("\uFEFF", "");
                }
                if (linhaAtual == 0) {
                    cabecalhoCSV = dados; 
                    linhaAtual++;
                }
                    csvPadronizado.add(dados);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!validarCabecalho(cabecalhoCSV)) {
            throw new CSVInvalidoException("O arquivo CSV não possui o cabeçalho esperado.");
        }
    }

    public List<Registro> criarRegistro(){
        VariavelClimatica service = new VariavelClimatica();
        List<Registro> registroFiltrado = new ArrayList<>();
        int ignorarCabecalho = 0;
        for (String[] linha : csvPadronizado) {
            if (ignorarCabecalho == 0 && linha.length > 0) {
                ignorarCabecalho++;
                continue;
            }
            if (automatico) {
                LocalDate data = LocalDate.parse(linha[0], formatadorDia);
                LocalTime hora = LocalTime.parse(linha[1],formatadorHora);

                String tempMax = linha[3];
                String tempMin = linha[4];
                String umiMax = linha[6];
                String umiMin = linha[7];
                String velVento = linha[14];
                String dirVento = linha[15];
                String chuva = linha[18];

                Double temperaturaMediaDouble = (tempMax != null && !tempMax.isEmpty() && tempMin != null && !tempMin.isEmpty()) 
                ? ((Double.parseDouble(tempMax) + Double.parseDouble(tempMin)) / 2) 
                : null;
                Double umidadeMediaDouble = (umiMax != null && !umiMax.isEmpty() && umiMin != null && !umiMin.isEmpty()) 
                ? ((Double.parseDouble(umiMax) + Double.parseDouble(umiMin)) / 2) 
                : null;
                Double velVentoDouble = (velVento != null && !velVento.isEmpty()) ? Double.parseDouble(velVento) : null;
                Double dirVentoDouble = (dirVento != null && !dirVento.isEmpty()) ? Double.parseDouble(dirVento) : null;
                Double chuvaDouble = (chuva != null && !chuva.isEmpty()) ? Double.parseDouble(chuva) : null;          
                Registro novoRegistro = new Registro(data, hora, codigoEstacao, siglaCidade, "temperaturaMedia", temperaturaMediaDouble, 
                    service.tipoSuspeito("temperaturaMedia", temperaturaMediaDouble));
                registroFiltrado.add(novoRegistro);
                novoRegistro = new Registro(data, hora, codigoEstacao, siglaCidade, "umidadeMedia", umidadeMediaDouble, service.tipoSuspeito("umidadeMedia", umidadeMediaDouble));
                registroFiltrado.add(novoRegistro);
                novoRegistro = new Registro(data, hora, codigoEstacao, siglaCidade, "velVento", velVentoDouble, service.tipoSuspeito("velVento", velVentoDouble));
                registroFiltrado.add(novoRegistro);
                novoRegistro = new Registro(data, hora, codigoEstacao, siglaCidade, "dirVento", dirVentoDouble, service.tipoSuspeito("dirVento", dirVentoDouble));
                registroFiltrado.add(novoRegistro);
                novoRegistro = new Registro(data, hora, codigoEstacao, siglaCidade, "chuva", chuvaDouble, service.tipoSuspeito("chuva", chuvaDouble));
                registroFiltrado.add(novoRegistro);
                } else {
                    LocalDate data = LocalDate.parse(linha[0],formatadorDia);
                    LocalTime hora = LocalTime.parse(linha[1],formatadorHora);
                    String tempHora = linha[2];
                    Double umidadeMedia = (linha[3] != null && !linha[3].isEmpty()) ? Double.parseDouble(linha[3]) : null;
                    Double velVento = (linha[5] != null && !linha[5].isEmpty()) ? Double.parseDouble(linha[5]) : null;
                    Double dirVento = (linha[6] != null && !linha[6].isEmpty()) ? Double.parseDouble(linha[6]) : null;
                    Double chuva = (linha[11] != null && !linha[11].isEmpty()) ? Double.parseDouble(linha[11]) : null;


                    // A variável temperaturaMedia é calculada da seguinte maneira:
                    // Se tempHora não é nulo e não está vazio, então o valor de tempHora é convertido para Double, 273 é subtraído desse valor, e o resultado é convertido para uma String.
                    // Caso contrário, temperaturaMedia é definida como null.
                    Double temperaturaMedia = (tempHora != null && !tempHora.isEmpty()) 
                    ? (Double.parseDouble(tempHora) - 273)
                    : null; 

                    Registro novoRegistro = new Registro(data, hora, codigoEstacao, siglaCidade, "temperaturaMedia", temperaturaMedia, 
                        service.tipoSuspeito("temperaturaMedia", temperaturaMedia));
                    registroFiltrado.add(novoRegistro);
                    novoRegistro = new Registro(data, hora, codigoEstacao, siglaCidade, "umidadeMedia", umidadeMedia, service.tipoSuspeito("umidadeMedia", umidadeMedia));
                    registroFiltrado.add(novoRegistro);
                    novoRegistro = new Registro(data, hora, codigoEstacao, siglaCidade, "velVento", velVento, service.tipoSuspeito("velVento", velVento));
                    registroFiltrado.add(novoRegistro); 
                    novoRegistro = new Registro(data, hora, codigoEstacao, siglaCidade, "dirVento", dirVento, service.tipoSuspeito("dirVento", dirVento));
                    registroFiltrado.add(novoRegistro);
                    novoRegistro = new Registro(data, hora, codigoEstacao, siglaCidade, "chuva", chuva, service.tipoSuspeito("chuva", chuva));
                    registroFiltrado.add(novoRegistro);
                }
        }
        // for (Registro registro : registroFiltrado) {
        //     System.out.println(registro.toString());
        // }
        // System.out.println("size: " + registroFiltrado.size());
        return registroFiltrado;
    }      

    private boolean validarCabecalho(String[] cabecalho){ 
        if(automatico){
            return validarCabecalhoComCampos(cabecalho, camposAutomatico);
        } else if (nomeInvalido){
            return validarCabecalhoComCampos(cabecalho, camposAutomatico) || validarCabecalhoComCampos(cabecalho, camposManual);
        }
        return validarCabecalhoComCampos(cabecalho, camposManual);
    }

    private boolean validarCabecalhoComCampos(String[] cabecalho, Map<String, Integer> camposEsperados) {
        for (String campoEsperado : camposEsperados.keySet()) {
            Integer posicaoEsperada = camposEsperados.get(campoEsperado);
            String valorCabecalho = cabecalho[posicaoEsperada];
            if(!valorCabecalho.equals(campoEsperado)) {
                return false;   
            }
        }
        return true;
    }

    private void inicializarHashMap(){
        camposAutomatico.put("Data", 0);
        camposAutomatico.put("Hora (UTC)", 1);
        camposAutomatico.put("Temp. Max. (C)", 3);
        camposAutomatico.put("Temp. Min. (C)",4);
        camposAutomatico.put("Umi. Max. (%)", 6);
        camposAutomatico.put("Umi. Min. (%)", 7);
        camposAutomatico.put("Vel. Vento (m/s)", 14);
        camposAutomatico.put("Dir. Vento (m/s)", 15);
        camposAutomatico.put("Chuva (mm)", 18);
        camposManual.put("Data", 0);
        camposManual.put("Hora (UTC)", 1);
        camposManual.put("Temp. [Hora] (K)", 2);
        camposManual.put("Umi. (%)", 3);
        camposManual.put("Vel. Vento (m/s)", 5);
        camposManual.put("Dir. Vento (m/s)", 6);
        camposManual.put("Chuva [Diaria] (mm)", 11);
    }

     private String obterNomeAquivo() {
        String nomecsv = new File(caminhoCSV).getName();
        return nomecsv;
    }

    private boolean lerNomeCSV() throws NomeCSVInvalidoException{
        String nomecsv = obterNomeAquivo();
        System.out.println("nome: " + nomecsv);
        String nomeSemExtensao = nomecsv.substring(0, nomecsv.lastIndexOf('.'));
        System.out.println("nome sem extensao: " + nomeSemExtensao);

        // Definir padrão para extrair informações do nome do arquivo
        Pattern pattern = Pattern.compile("^(A|)(\\d+)_([A-Z]{2,})$");

        // Extrair informações do primeiro nome de arquivo
        Matcher matcher = pattern.matcher(nomeSemExtensao);
        if (matcher.matches()) {
            String modoColeta = matcher.group(1); // Modo de coleta (A ou vazio)
            codigoEstacao = matcher.group(2); // Código da estação
            siglaCidade = matcher.group(3); // Código da cidade

            if ("A".equals(modoColeta)) {
                modoColeta = "Automatico";
            }else{
                modoColeta = "Manual";
                automatico = false;
            }

            System.out.println("Modo de Coleta: " + modoColeta);
            System.out.println("Código da Estação: " + codigoEstacao);
            System.out.println("Código da Cidade: " + siglaCidade);
            return true;
        } else{
            throw new NomeCSVInvalidoException("O nome do arquivo CSV não está no formato esperado.");
        }
    }

}
