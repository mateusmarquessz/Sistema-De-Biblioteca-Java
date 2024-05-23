package model;

public class Emprestimo {
    private int idEmprestimo;
    private int idLivro;
    private int idAutor;
    private String titulo;
    private String descricao;
    private String dataDeCadastro;

    public Emprestimo() {
    }


    public int getIdEmprestimo() {
        return idEmprestimo;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getDataDeCadastro() {
        return dataDeCadastro;
    }


    public void setIdEmprestimo(int idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataDeCadastro(String dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
    }
}
