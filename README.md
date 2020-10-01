# projeto-es1

Projeto da disciplina de Engenharia de Software 1, do curso de Sistemas de Informação, desenvolvido por alunos do 5 semestre. Ferramentas utilizadas: Spring Boot, Spring MVC, Spring Web, Spring Security ,Spring Data ,Spring Session, Spring Boot DevTools, PostgreSQL, Gradle, Thymeleaf, Lombok, Bootstrap.

---
## Documentação de funcionamento 
### Branches

**master**:Responsável por guardar as versões do projeto funcionais (testadas e homologadas)\
**develop**:Guardar as features desenvolvidas e já testadas\
**feature**:Guardar os commits de produção da feature atual (Ex: feature/login)\
**release**:Depois de passar do estágio de feature e de desenvolvimento, irá para release para ser homologada e já funcional. **Ex:** release/login, release/cliente, release/carrinho\
**bugfix**:Em caso um bug em produção, utiliza-se esta branch para corrigir o mesmo e mergear com a master.

---

### Commits

- **docs**:Para commits de documentação (Ex: Readme e configurações) 
  - **Ex:** “docs: Adição do texto de configuração do README.md”
- **feature**:Para commits de feature de merge com a branch de desenvolvimento. 
  - **Ex:** “feature: Implementado página de login”z
- **release**:Para commits que devem ser mergeados com a master
  - **Ex:** ”release: versão 1.0.0, login de usuário funcional”
- **bugfix**: Para commits que devem ser de correção a bugs na branch master
  - **Ex:** ”bugfix: Correção do bug de usuário logar sem senha”
- **prod**: Para commits na release atual que está sendo desenvolvida.
  - **Ex:** “prod: Implementação da página de login”
  - **Ex:** “prod: Implementação do campo de login de usuário”
  - **Ex:** “prod: Implementação do botão na cor azul”