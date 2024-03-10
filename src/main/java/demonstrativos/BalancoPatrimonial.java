package demonstrativos;

import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.math.BigDecimal;

public class BalancoPatrimonial {

    private HashMap<String, ArrayList<RegistroPatrimonial>> categorias;

    public BalancoPatrimonial(){
        categorias = new HashMap<>();
        categorias.put("AC", new ArrayList<>());
        categorias.put("ANC", new ArrayList<>());
        categorias.put("PC", new ArrayList<>());
        categorias.put("PNC", new ArrayList<>());
        categorias.put("PL", new ArrayList<>());
    }

    public boolean adicionarRegistro(String categoria, String nome, double valor){
        if(categorias.get(categoria) == null){
            return false;
        }
        
        categorias.get(categoria).add(new RegistroPatrimonial(nome, valor));
        return true;
    }

    public boolean removerRegistro(String categoria, int selecionado){
        if(!indiceValido(categoria, selecionado)){
            return false;
        }
        
        categorias.get(categoria).remove(selecionado - 1);
        return true;
    }

    public boolean alterarRegistro(String categoria, String nome, int valor, int selecionado){
        if(!indiceValido(categoria, selecionado)){
            return false;
        }
        
        categorias.get(categoria).set(selecionado - 1, new RegistroPatrimonial(nome, valor));
        return true;
    }
    
    public boolean alterarRegistro(String categoria, int valor, int selecionado){
        if(!indiceValido(categoria, selecionado)){
            return false;
        }
        
        String nome = categorias.get(categoria).get(selecionado - 1).getNome();
        categorias.get(categoria).set(selecionado - 1, new RegistroPatrimonial(nome, valor));
        return true;
    }
        
    public boolean alterarRegistro(String categoria, String nome, int selecionado){
        if(!indiceValido(categoria, selecionado)){
            return false;
        }
        
        BigDecimal valor = categorias.get(categoria).get(selecionado - 1).getValor();
        categorias.get(categoria).set(selecionado - 1, new RegistroPatrimonial(nome, valor));
        return true;
    }

    
    public boolean indiceValido(String categoria, int indice){
        return indice >= 1 && indice <= categorias.get(categoria).size();
    }
    
    public void imprimirRegistro(String categoria, int selecionado){
        System.out.println(categorias.get(categoria).get(selecionado - 1));
    }
    
    public void imprimirRegistros(String categoria){
        for(int i = 0; i < categorias.get(categoria).size(); i++){
            System.out.println((i + 1) + ": " + categorias.get(categoria).get(i));
        }
    }

    public boolean lerArquivo(String local){
        try(Scanner arquivo = new Scanner(Paths.get(local))){
            while(arquivo.hasNextLine()){
                String linha = arquivo.nextLine();
                if(linha.isBlank()){
                    break;
                }

                String[] partesRegistro = linha.split("\\|");
                String categoria = partesRegistro[0].trim();
                String nome = partesRegistro[1].trim();
                double valor = Double.parseDouble(partesRegistro[2].trim());
                
                categorias.get(categoria).add(new RegistroPatrimonial(nome, valor));
            }
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public boolean gerarArquivo(String local){
        try(FileWriter arquivoSaida = new FileWriter(new File(local))){
            for(String categoria : categorias.keySet()){
                for(RegistroPatrimonial registro : categorias.get(categoria)){
                    arquivoSaida.write(categoria + " | " + registro + "\n");
                }
            }
            
            arquivoSaida.write("\nTOTAL ATIVO = " + totalAtivo());
            arquivoSaida.write("\nTOTAL PASSIVO + PATRIMONIO LIQUIDO = " + totalPassivo().add(somaRegistros("PL")));
            return true;
        } catch (Exception e){
            return false;
        }
    }
    
    private BigDecimal totalAtivo(){
        BigDecimal total = new BigDecimal(0);
        total = total.add(somaRegistros("AC"));
        total = total.add(somaRegistros("ANC"));
        
        return total;
        
    }
    
    private BigDecimal totalPassivo(){
        BigDecimal total = new BigDecimal(0);
        total = total.add(somaRegistros("PC"));
        total = total.add(somaRegistros("PNC"));
        
        return total;
    }
    
    private BigDecimal somaRegistros(String categoria){
        BigDecimal total = new BigDecimal(0);
        for(RegistroPatrimonial registro : categorias.get(categoria)){
            total = total.add(registro.getValor());
        }
        return total;
    }
        
    public boolean estaVazio(String categoria){
        return categorias.get(categoria).isEmpty();
    }
}
