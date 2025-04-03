# 🏋️‍♂️ API de Academia

<p align="center">
  <img src="image.png" alt="API de Academia">
</p>

Esta é uma API para gerenciamento de academias, oferecendo recursos para manipulação de alunos, usuários e autenticação.

## 🚀 Tecnologias Utilizadas
- **Java** - Linguagem de programação
- **Spring Boot** - Framework para construção de APIs
- **Spring Data JPA** - Gerenciamento de persistência
- **PostgreSQL** - Banco de dados relacional
- **Maven** - Gerenciamento de dependências
- **Spring Security** - Controle de autenticação e autorização
- **JSON Web Token (JWT)** - Autenticação baseada em tokens
- **Swagger** - Documentação interativa da API

## ⚙️ Configuração do Ambiente
1. **Instalar o JDK**: Baixe e instale o JDK mais recente do site oficial da Oracle.
2. **Verificar o Maven**: Confirme a instalação do Maven executando `mvn -v` no terminal.
3. **Clonar o Repositório**:
   ```sh
   git clone https://github.com/paulohenr96/api-academia.git
   ```
4. **Importar o Projeto**: Abra sua IDE (Eclipse, IntelliJ, etc.) e importe o projeto como um projeto Maven existente.
5. **Configurar o Banco de Dados**:
   - No arquivo `application.properties` (localizado em `src/main/resources`), ajuste as credenciais de conexão com o PostgreSQL.
6. **Executar a API**: Inicie a aplicação e a API estará disponível na porta configurada.

## 📌 Endpoints Disponíveis
### 🎓 AlunoController
- **GET** `/alunos/` - Lista todos os alunos.
- **GET** `/alunos/{id}` - Obtém detalhes de um aluno pelo ID.
- **POST** `/alunos/` - Cria um novo aluno.
- **PUT** `/alunos/{id}` - Atualiza informações de um aluno.
- **DELETE** `/alunos/{id}` - Remove um aluno.
- **GET** `/alunos/{id}/mensalidades` - Obtém as mensalidades de um aluno.
- **POST** `/alunos/{id}/mensalidades` - Registra o pagamento de uma mensalidade.
- **GET** `/alunos/devedores/{mes}` - Lista alunos com mensalidades em atraso para um determinado mês.

### 👤 UsuarioController
- **GET** `/usuario/` - Lista todos os usuários.
- **GET** `/usuario/{id}` - Obtém um usuário pelo ID.
- **POST** `/usuario/` - Cria um novo usuário.
- **PUT** `/usuario/{id}` - Atualiza um usuário.
- **DELETE** `/usuario/{id}` - Remove um usuário.

### 🔐 LoginController
- **POST** `/login` - Realiza o login e retorna um token JWT.

## 📂 DTOs (Data Transfer Objects)
- **`LoginDTO`** - Contém `username` e `password` para autenticação.
- **`MensalidadeDTO`** - Utilizado para pagamentos de mensalidades (`dataPagamento`).
- **`PaginacaoDTO`** - Controla a paginação na listagem de alunos devedores.
- **`UsuarioDTO`** - Retorna informações relevantes do usuário.

## ⚠️ Exceções Personalizadas
- **`MensalidadePagaException`** - Lançada quando uma mensalidade já foi quitada.
- **`UserNotFoundException`** - Lançada quando um usuário não é encontrado.

## 🔐 Segurança e Autenticação
### 🔑 Componentes de Segurança
- **`Constantes`** - Define valores como prefixo do token e tempo de validade.
- **`CriadorToken`** - Gera e valida tokens JWT.
- **`SecurityConfig`** - Configuração do Spring Security.
- **`ObjetoSessao`** - Retorna o token JWT após o login.
- **`FilterToken`** - Filtra requisições para validar tokens JWT.

## 🏗️ Estrutura do Projeto
- **Model** - Classes representando entidades (Aluno, Mensalidade, Role, Usuario).
- **Repository** - Interfaces para acesso ao banco de dados (Aluno, Mensalidade, Usuario).
- **Service** - Camada de lógica de negócio para Aluno, Senha, UsuarioDetailsService e Usuario.
- **SwaggerConfig** - Configuração do Swagger para documentação da API.

## 🤝 Contribuição
Contribuições são bem-vindas! Para colaborar:
1. Faça um fork do repositório.
2. Crie uma branch para suas alterações.
3. Envie um pull request com a descrição das mudanças.

## 📜 Licença
Este projeto está licenciado sob a licença MIT. Consulte o arquivo `LICENSE` para mais detalhes.

## 📬 Contato
Dúvidas ou sugestões? Entre em contato via e-mail: paulohsantos281096@gmail.com.

🚀 API pronta para otimizar o gerenciamento de academias! 💪

