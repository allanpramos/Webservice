# Webservice

======= COMO USAR =======

Para realizar os cálculos, basta digitar os seguintes comandos na barra de pesquisa do seu navegador:

1. Operações matemáticas

  SERVIDOR SIMPLES
  - SOMAR: localhos:8081/calcular/N1/+/N2
  - SUBTRAIR: localhos:8081/calcular/N1/-/N2
  - MULTIPLICAR: localhos:8081/calcular/N1/*/N2
  - DIVIDIR: localhos:8081/calcular/N1/:/N2

  SERVIDOR COMPLEXO
  - PORCENTAGEM: localhos:8081/calcular/N1/por/N2
  - POTENCIAÇÃO: localhos:8081/calcular/N1/pot/N2
  - RADICIAÇÃO: localhos:8081/calcular/N1/rad/N2


2. Como Executar
    - Para executar o projeto, você deve executar primeiramente os SERVIDORES (ServerComplexas.java e ServerSimples.java) e depois executar o MAIN (Main.java). Após a execução a calculadora estará pronta para receber as expressões a serem calculadas
  

3. Explicação do projeto
    - Sobre o Projeto: 
    O projeto foi criado usando o conceito aprendido na disciplina de sistemas distribuídos. De forma resumida, o servidor SIMPLES fica responsável por realizar as quatro operações matematicas básicas (Somar, Subitrir, Dividir e Multiplicar), já o servidor COMPLEXO, é responsável pelas operações mais complexas(Porcentagem, Potenciação e Radiciação). Também foi criado um Main onde fica a API que é responsavel por receber a mensagem que é passada e destina-lá de forma correrta para os servidores. Além disso a informação que é digitada pelo usuário vai ser verificada e se existir algum erro na expressão, uma mensagem de erro será imprimida na tela, isso acontece antes mesmo da menssagem chegar a qualquer um dos servidores. Caso nenhum erro seja encontrado a mensagem é enviada para o servidor especifico para aquele cálculo. Depois do cálculo, o resultado é enviado para o MAIN e o mesmo é mostrado na tela do navegador. 
    
    - O protocolo:
    O protocolo foi criado usando a linguagem de programação JAVA. Ele foi feito com base na leitura de uma expressão para o cálculo, sendo essa expressão composta por dois operanos e um operador que devem ser separados pelo caractere / para que sejam execultados da forma correta. Caso seja qualquer expressao diferente disso, uma mensagem de erro deve ser exibida ao usuário
    
4. O host do main : 127.0.1.1

5. Para o servidor SIMPLES foi usado a porta:9999 para realiza operações básicas(Somar, Subitrir, Dividir e Multiplicar)
6. Para o servidor COMPLEXO foi usado a porta:12345 para realiza operações complexas(Porcentagem, Potenciação e Radiciação)
