package com.example.dadosmeteorologicos.Services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.dadosmeteorologicos.exceptions.CSVInvalidoException;
import com.example.dadosmeteorologicos.exceptions.NomeCSVInvalidoException;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

public class CSVResolve {

    private String caminhoCSV;
    
    private List<String[]> csvPadronizado = new ArrayList<>();
    @Getter
    @Setter
    private boolean automatico = true;
    @Getter
    private boolean nomeInvalido = false;
    @Getter
    @Setter
    private String codigoEstacao = "";
    @Getter
    @Setter
    private String codigoCidade = "";
    private String[] cabecalhoCSV = null;
    private Map<String, Integer> camposAutomatico = new HashMap<>();
    private Map<String, Integer> camposManual = new HashMap<>();


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

 public List<String[]> filtrarCSV(){
        List<String[]> filtrarCSVPadronizado = new ArrayList<>();
        int ignorarCabecalho = 0;
        for (String[] linha : csvPadronizado) {
            if (ignorarCabecalho == 0 && linha.length > 0) {
                ignorarCabecalho++;
                continue;
            }
            String novaLinha = "";
            if (automatico) {
                // Variaveis para calculo da media
                String tempMax = linha[3];
                String tempMin = linha[4];
                String umiMax = linha[6];
                String umiMin = linha[7];

                // Variaveis para o registro
                String data = linha[0];
                String hora = linha[1];
                // A variável umidadeMedia é calculada da seguinte maneira:
                // Se umiMax e umiMin não são nulos e não estão vazios, então a média de umiMax e umiMin é calculada e convertida para uma String.
                // Caso contrário, umidadeMedia é definida como null.    
                String umidadeMedia = (umiMax != null && !umiMax.isEmpty() && umiMin != null && !umiMin.isEmpty()) 
                    ? String.valueOf((Double.parseDouble(umiMax) + Double.parseDouble(umiMin)) / 2) 
                    : null;
                String velVento = linha[14];
                String dirVento = linha[15];
                String chuva = linha[18];
                String temperaturaMedia = (tempMax != null && !tempMax.isEmpty() && tempMin != null && !tempMin.isEmpty()) 
                    ? String.valueOf((Double.parseDouble(tempMax) + Double.parseDouble(tempMin)) / 2) 
                    : null;
                // Formata a nova linha
                novaLinha = String.format(Locale.US, "%s;%s;%s;%s;%s;%s;%s;%s;%s",
                    codigoCidade, codigoEstacao,
                    data, hora,
                    temperaturaMedia != null ? String.format(Locale.US, "%.1f", tentarParseDouble(temperaturaMedia)) : null,
                    umidadeMedia != null ? String.format(Locale.US, "%.1f", tentarParseDouble(umidadeMedia)) : null,
                    velVento != null ? String.format(Locale.US, "%.1f", tentarParseDouble(velVento)) : null,
                    dirVento != null ? String.format(Locale.US, "%.1f", tentarParseDouble(dirVento)) : null,
                    chuva != null ? String.format(Locale.US, "%.1f", tentarParseDouble(chuva)) : null);

                } else {
                    // Variaveis para o registro
                    String data = linha[0];
                    String hora = linha[1];
                    String tempHora = linha[2];
                    String umidadeMedia = linha[3];
                    String velVento = linha[5];
                    String dirVento = linha[7];
                    String chuva = linha[11];
                    // A variável temperaturaMedia é calculada da seguinte maneira:
                    // Se tempHora não é nulo e não está vazio, então o valor de tempHora é convertido para Double, 273 é subtraído desse valor, e o resultado é convertido para uma String.
                    // Caso contrário, temperaturaMedia é definida como null.
                    String temperaturaMedia = (tempHora != null && !tempHora.isEmpty()) 
                    ? String.valueOf(tentarParseDouble(tempHora) - 273)
                    : null; 
                    // Formata a nova linha
                    novaLinha = String.format(Locale.US, "%s;%s;%s;%s;%s;%s;%s;%s;%s",
                        codigoCidade, codigoEstacao,
                        data, hora,
                        temperaturaMedia != null ? String.format(Locale.US, "%.1f", tentarParseDouble(temperaturaMedia)) : null,
                        umidadeMedia != null ? String.format(Locale.US, "%.1f", tentarParseDouble(umidadeMedia)) : null,
                        velVento != null ? String.format(Locale.US, "%.1f", tentarParseDouble(velVento)) : null,
                        dirVento != null ? String.format(Locale.US, "%.1f", tentarParseDouble(dirVento)) : null,
                        chuva != null ? String.format(Locale.US, "%.1f", tentarParseDouble(chuva)) : null);
            }
            filtrarCSVPadronizado.add(novaLinha.split(";"));
        }
        return filtrarCSVPadronizado;
    }

    private boolean validarCabecalho(String[] cabecalho){
        boolean cabecalhoValidoAutomatico = validarCabecalhoComCampos(cabecalho, camposAutomatico);
        boolean cabecalhoValidoManual = validarCabecalhoComCampos(cabecalho, camposManual);
        if (cabecalhoValidoManual) {
            automatico = false;
        }
        return cabecalhoValidoAutomatico || cabecalhoValidoManual;
    }

    private boolean validarCabecalhoComCampos(String[] cabecalho, Map<String, Integer> camposEsperados) {
        for (String campoEsperado : camposEsperados.keySet()) {
            Integer posicaoEsperada = camposEsperados.get(campoEsperado);
            if(!cabecalho[posicaoEsperada].equals(campoEsperado)) {
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

    private static Double tentarParseDouble(String s) {
        try {
            return s == null ? null : Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

     private String obterNomeAquivo() {
        String nomecsv = caminhoCSV.substring(caminhoCSV.lastIndexOf('\\') + 1);
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
            codigoCidade = matcher.group(3); // Código da cidade

            if ("A".equals(modoColeta)) {
                modoColeta = "Automatico";
            }else{
                modoColeta = "Manual";
                automatico = false;
            }

            System.out.println("Modo de Coleta: " + modoColeta);
            System.out.println("Código da Estação: " + codigoEstacao);
            System.out.println("Código da Cidade: " + codigoCidade);
            return true;
        } else{
            throw new NomeCSVInvalidoException("O nome do arquivo CSV não está no formato esperado.");
        }
    }

}
