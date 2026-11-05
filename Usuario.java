public class Usuario {

    public String login;
    public String senha;

    public Usuario(String login, String senha){
        this.login = login;
        this.senha = senha;
    }

    public boolean verificar(String loginDigitado, String senhaDigitada){
        if(login.equals(loginDigitado) && senha.equals(senhaDigitada)){
            return true;
        } else {
            return false;
        }
    }
}
