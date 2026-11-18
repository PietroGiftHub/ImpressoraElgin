# 🖨️ Sistema de Controle de Impressora Elgin
### *(Impressora Elgin)*
---

## 📘 Sobre o Projeto

Este projeto foi desenvolvido por alunos do 2º ano do Ensino Médio para se comunicar com uma impressora térmica **Elgin i8/i9** usando a biblioteca `E1_Impressora01.dll`. O sistema tem login e um menu com operações de impressão. Faz parte da disciplina de Programação Orientada a Objetos, pois usa conceitos de classes, atributos e métodos.

---

## 🧰 Tecnologias Utilizadas

- **Java** (25)
- **IntelliJ IDEA**
- **JNA 5.15.0** (comunicação com a DLL)
- **DLL E1_Impressora01** (SDK da Elgin)
- **Impressora Elgin i8 ou i9**

---

## 📁 Estrutura do Projeto

```text
/src
   Main.java              → Responsável pelo início do programa, login e menu principal.
   Usuario.java           → Armazena os dados do usuário e realiza a verificação de login.
   Impressora.java        → Responsável pela configuração da conexão e execução das operações da impressora.

/Bibliotecas E1 Impressora → Contém as bibliotecas DLL da Elgin para as arquiteturas x64 e x86.

/libs Java                → Contém a biblioteca jna-5.15.0.jar, utilizada na comunicação entre Java e DLL.
```

---

## ⚙️ Instalação e Execução

### 1. Baixar o projeto
1. Acesse o repositório no GitHub.
2. Clique em **Code → Download ZIP**.
3. Extraia o arquivo.

### 2. Abrir no IntelliJ IDEA
1. **File → Open** e escolha a pasta extraída.
2. Em **File → Project Structure → Libraries**, confirme que o `jna-5.15.0.jar` está adicionado.
3. Conecte e ligue a impressora.
4. Abra `Main.java` e clique em ▶️ **Run**.

### 3. Configurar e utilizar a impressora
1. Realize o login utilizando o usuário e a senha disponibilizados neste README.
2. No menu principal, selecione a opção 1 - Configurar conexão.
3. Informe o tipo de conexão, o modelo da impressora, os dados de conexão e o parâmetro solicitado (no caso principal, USB).
4. Selecione a opção 2 - Abrir conexão.
5. Após estabelecer a conexão, utilize as demais opções do menu para realizar as operações de impressão que desejar.
6. Quando terminar de usar, selecione 0 - Encerrar o programa.

---

## 🔐 Login

Ao executar o programa, serão solicitados usuário e senha, que são estes, respectivamente:

**Usuário:** admin

**Senha:** 1234

Você terá 3 tentativas para acertar. Caso erre algum dos dados, o programa será encerrado automaticamente após as 3 tentativas.

---

## 📋 Funcionalidades do Menu

| Opção | O que faz |
|---|---|
| 1 | Configura os parâmetros de conexão com a impressora. |
| 2 | Abre a conexão com a impressora Elgin. |
| 3 | Fecha a conexão com a impressora. |
| 4 | Realiza a impressão de um texto informado pelo usuário. |
| 5 | Gera e imprime um QR Code a partir dos dados informados. |
| 6 | Gera e imprime um código de barras. |
| 7 | Realiza a impressão de um XML SAT. |
| 8 | Realiza a impressão de um XML de cancelamento SAT. |
| 9 | Aciona a abertura do sensor da gaveta. |
| 10 | Emite um sinal sonoro pela impressora (máximo de 5). |
| 0 | Encerra o programa. |

---

## 🧪 Testes Realizados

- Teste de login com credenciais corretas.
- Teste de login com senha incorreta e o limite de 3 tentativas.
- Configuração e abertura da conexão com a impressora, verificando se o retorno é 0 ou não.
- Fechamento da conexão.
- Teste realizado em sala para verificar se o usuário conseguia digitar.
- Os demais métodos foram testados apenas por meio do código e ainda não foram testados fisicamente. Pelo que tudo indica, estão funcionando corretamente.

---

## 📚 Documentação Adicional

- Trabalho_Impressora_Elgin.pdf
- [Documentação do SDK E1_Impressora](https://elgindevelopercommunity.github.io/group___m1.html)

---

## 👥 Autores

- Pedro Gomes do Nascimento
- Pietro Mandu Gama
- Ricardo Castro Xavier De Brito