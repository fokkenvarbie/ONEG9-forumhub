<h1>ForumHub</h1>

Uma API REST desenvolvida em Java com Spring Boot que simula o funcionamento de um fórum de discussões. O sistema permite o gerenciamento completo de tópicos, integrando segurança com tokens JWT e persistência em banco de dados relacional. A aplicação organiza as interações dos usuários e exibe as informações de forma estruturada e segura.

- **Funcionalidades:**
  - **Autenticação de Usuários:** Sistema de login seguro que gera tokens JWT para autenticar as requisições e proteger as rotas da API.
  - **Gerenciamento de Tópicos:** Permite a criação (POST), consulta (GET), atualização (PUT) e exclusão (DELETE) de tópicos de discussão.
  - **Listagem Paginada:** Exibe os tópicos cadastrados com suporte a paginação e ordenação automática por data de criação.
  - **Validação de Dados:** Garante que todos os campos obrigatórios sejam preenchidos e que não existam tópicos duplicados no sistema.
  - **Persistência de Dados:** Armazena todas as informações de usuários, tópicos e cursos de forma robusta em um banco de dados PostgreSQL.
  - **Documentação Interativa:** Interface visual via Swagger UI que permite testar todos os endpoints da API diretamente pelo navegador.
