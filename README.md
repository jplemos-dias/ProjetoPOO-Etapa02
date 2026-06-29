# VidaPlena - Sistema de Gestao de Clinica

Sistema feito em Java para gerenciar uma clinica multidisciplinar. Da pra cadastrar pacientes, profissionais, agendar consultas, registrar atendimentos e processar pagamentos.

## O que da pra fazer

- Cadastrar pacientes (minimo ou completo) e complementar depois
- Cadastrar profissionais por especialidade (fisioterapia, psicologia, nutricao, clinica geral)
- Agendar consultas por profissional ou por especialidade
- Cancelar e remarcar consultas
- Registrar atendimentos com prontuario
- Processar pagamentos em dinheiro, cartao ou convenio
- Desativar pacientes
- Gerar relatorios (geral, por profissional, por periodo, financeiro, unificado)
- Exportar dados do sistema

## Como compilar e rodar

Precisa ter o JDK 11 ou superior. No terminal entra na pasta src e roda:

```
javac *.java
java Main
```

## Como usar

O sistema funciona por menus no terminal. Ao abrir aparece o menu principal:

- 1 - Pacientes (cadastrar, buscar, complementar, desativar)
- 2 - Profissionais (cadastrar, atualizar dados)
- 3 - Consultas (agendar, cancelar, remarcar)
- 4 - Atendimentos (registrar com observacoes e diagnostico)
- 5 - Pagamentos (dinheiro com 5% desconto, cartao ate 6x, convenio)
- 6 - Relatorios (geral, por profissional, periodo, financeiro, unificado, exportar)
- 0 - Sair

Pra agendar uma consulta precisa ter o paciente e o profissional cadastrados antes. Pra registrar pagamento a consulta precisa ter sido atendida.

## Integrantes

- Joao Pedro
- Joao Lourenco
