# API de Academia
<p align="center">
  <img src="image.png" alt="alt text">
</p>
Esta é uma API para gerenciamento de academias, oferecendo recursos para manipulação de alunos, usuários e autenticação.

## Tecnologias utilizadas
- Java
- Spring Boot
- Spring Data JPA
- PostgresSQL
- Maven
- Spring Security
- JSON Web Token (JWT)
- Swagger

## Configuração do ambiente
1. Certifique-se de ter o JDK (Java Development Kit) instalado em sua máquina. Você pode baixá-lo e instalá-lo a partir do site oficial da Oracle.
2. Verifique se o Maven está instalado. O Maven é uma ferramenta de automação de compilação e gerenciamento de dependências. Você pode verificar a instalação executando o comando `mvn -v` no terminal.
3. Clone este repositório em sua máquina local:
```
git clone https://github.com/paulohenr96/api-academia.git
```
4. Importe o projeto em sua IDE de preferência (Eclipse, IntelliJ, etc.) como um projeto Maven existente.
5. Configure as informações de conexão com o banco de dados MySQL no arquivo `application.properties`, localizado em `src/main/resources`. Certifique-se de fornecer o URL do banco de dados, nome de usuário e senha corretos.
6. Execute a aplicação. A API será iniciada na porta definida no arquivo de configuração.

## Endpoints
### AlunoController
- GET `/alunos/`: Recupera todos os alunos.
- GET `/alunos/{id}`: Recupera um aluno pelo ID.
- POST `/alunos/`: Cria um novo aluno.
- PUT `/alunos/{id}`: Atualiza as informações de um aluno existente.
- DELETE `/alunos/{id}`: Exclui um aluno pelo ID.
- GET `/alunos/{id}/mensalidades`: Recupera as mensalidades de um aluno.
- POST `/alunos/{id}/mensalidades`: Realiza o pagamento de uma mensalidade de um aluno.
- GET `/alunos/devedores/{mes}`: Recupera os alunos devedores do mês especificado.

### UsuarioController
- GET `/usuario/`: Recupera todos os usuários.
- GET `/usuario/{id}`: Recupera um usuário pelo ID.
- POST `/usuario/`: Cria um novo usuário.
- PUT `/usuario/{id}`: Atualiza as informações de um usuário existente.
- DELETE `/usuario/{id}`: Exclui um usuário pelo ID.

### LoginController
- POST `/login`: Realiza o login e retorna o token JWT.

## DTOs
- `LoginDTO`: DTO para a autenticação do usuário com os campos `username` e `password`.
- `MensalidadeDTO`: DTO para realizar o pagamento de uma mensalidade com o campo `dataPagamento`.
- `PaginacaoDTO`: DTO usado para a paginação dos alunos devedores.
- `UsuarioDTO`: DTO que retorna os dados do usuário que não são importantes.

## Exceções
- `MensalidadePagaException`: Exceção personalizada lançada quando uma mensalidade já foi paga.
- `UserNotFoundException`: Exceção personalizada lançada quando um usuário não é encontrado.

## Security
- `Constantes`: Classe contendo constantes utilizadas no sistema de segurança, como o prefixo do token, tempo de duração e outros.
- `CriadorToken`: Classe responsável por criar o token JWT caso seja nulo
 e também é usada para verificar se o token usado pelo usuário é válido.
- `SecurityConfig`: Configurações necessárias do Spring Security para autenticação e autorização.
- `ObjetoSessao`: Objeto usado para retornar o token JWT para o usuário que realizar o login.

## Outros componentes
- `FilterToken`: Filtro usado para verificar se o usuário está usando um token válido.
- Model: Classes de modelo para Aluno, Mensalidade, Role e Usuario.
- Repository: Repositórios para Aluno, Mensalidade e Usuario.
- Service: Classes de serviço para Aluno, Password, UsuarioDetailsService e Usuario.
- `SwaggerConfig`: Classe de configuração do Swagger para documentação da API.

## Contribuição
Sinta-se à vontade para contribuir com melhorias para este projeto. Faça um fork do repositório, faça suas alterações e envie um pull request.

## Licença
Este projeto está licenciado sob a licença MIT. Consulte o arquivo `LICENSE` para obter mais informações.

## Contato
Se você tiver alguma dúvida ou sugestão em relação a este projeto, entre em contato através do email: paulohsantos281096@gmail.com
