# 🌍 Relógio Mundial TCP/UDP (Sistemas Distribuídos)

## 📝 Resumo do Projeto
O projeto **Relógio Mundial TCP/UDP** é uma aplicação cliente-servidor desenvolvida em Java com o objetivo de consultar a hora atual de diferentes regiões do mundo (fusos horários). 
A atividade propõe a construção e evolução da comunicação em rede, passando pela implementação com o protocolo UDP, seguida por uma versão TCP iterativa (Single-thread) e, por fim, uma versão TCP concorrente (Multi-thread). O objetivo principal é compreender na prática as diferenças de implementação e desempenho entre esses diferentes modelos e protocolos de rede.

---

## 🚀 Funcionalidades Principais

### ⏱️ Consulta de Fuso Horário via UDP (Versão 1)
- Cliente envia uma requisição com o nome da região (ex: `America/Sao_Paulo`).
- Servidor recebe o datagrama, processa a hora usando a API `java.time` e retorna.
- Implementação de um *timeout* de 5 segundos no cliente para evitar espera infinita caso o servidor esteja offline.

### 🔗 Consulta via TCP Single-Thread (Versão 2)
- Conexão orientada a estado utilizando Sockets TCP.
- O servidor atende a um cliente por vez, de forma sequencial.

### 🔀 Consulta via TCP Multi-Thread (Versão 3)
- O servidor TCP delega o processamento de cada novo cliente para uma `Thread` independente.
- Capacidade de atender múltiplos clientes de forma simultânea.

---

## 🛠 Tecnologias Utilizadas

### 💻 Linguagem e Bibliotecas
- **Java (JDK 17+)**
- **Java Sockets API**: `java.net.Socket`, `java.net.ServerSocket`, `java.net.DatagramSocket`, `java.net.DatagramPacket`
- **Java Time API**: `java.time.ZonedDateTime`, `java.time.ZoneId` para manipulação precisa de fusos horários.
- **Threads (Runnable)**: Para concorrência na versão TCP Multi-thread.

---

## 📂 Organização de Pastas

A estrutura do projeto está dividida em pacotes dentro do diretório `src`:

```text
Rel-gio-Mundial-TCP-UDP/
├── src/
│   ├── udp_clock/                 # Versão 1: Implementação usando protocolo UDP
│   │   ├── ClienteUDP.java
│   │   └── ServidorUDP.java
│   ├── tcp_clock_simple/          # Versão 2: Implementação TCP Sequencial (Single-thread)
│   │   ├── ClienteTCP.java
│   │   └── ServidorTCPSingle.java
│   └── tcp_clock_multithread/     # Versão 3: Implementação TCP Concorrente (Multi-thread)
│       ├── ClienteTCP.java        
│       └── ServidorTCPMulti.java
└── README.md   
```

# Documentação do projeto
👥 Contribuições da Equipe
<table>
<tr>
<td align="center">
<a href="https://github.com/ricardoissadesousa">
<img src="https://github.com/ricardoissadesousa.png" width="100px;" alt="Foto do Ricardo"/>


<sub>
<p>Ricardo Issa de Sousa</p>
<b>Função: Desenvolvimento Sockets, Threads, Documentação</b>
</sub>
</a>
</td>
