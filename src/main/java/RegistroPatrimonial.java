
import java.math.BigDecimal;
import java.math.RoundingMode;

public class RegistroPatrimonial {

    private String nome;
    private BigDecimal valor;

    public RegistroPatrimonial(String nome, double valor) {
        this.nome = nome;
        this.valor = new BigDecimal(valor).setScale(2, RoundingMode.HALF_EVEN);
    }

    public RegistroPatrimonial(String nome, BigDecimal valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return nome + " " + valor;
    }
}
