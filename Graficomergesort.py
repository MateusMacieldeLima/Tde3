import pandas as pd
import matplotlib.pyplot as plt

# Lê o arquivo CSV gerado no Java
df = pd.read_csv('TDE3/relatorio_merge_sort.csv')

# Gráfico de tempo de execução para diferentes tamanhos de vetor
plt.figure(figsize=(10, 6))
plt.plot(df['Tamanho do Vetor'], df['Tempo de Execução (ms)'], marker='o', color='b', label='Tempo de Execução (ms)')
plt.xlabel('Tamanho do Vetor')
plt.ylabel('Tempo de Execução (ms)')
plt.title('Tempo de Execução em Função do Tamanho do Vetor (Merge Sort)')
plt.legend()
plt.grid(True)
plt.show()

# Gráfico de número de trocas para diferentes tamanhos de vetor
plt.figure(figsize=(10, 6))
plt.plot(df['Tamanho do Vetor'], df['Número de Trocas'], marker='o', color='r', label='Número de Trocas')
plt.xlabel('Tamanho do Vetor')
plt.ylabel('Número de Trocas')
plt.title('Número de Trocas em Função do Tamanho do Vetor (Merge Sort)')
plt.legend()
plt.grid(True)
plt.show()

# Gráfico de número de iterações para diferentes tamanhos de vetor
plt.figure(figsize=(10, 6))
plt.plot(df['Tamanho do Vetor'], df['Número de Iterações'], marker='o', color='g', label='Número de Iterações')
plt.xlabel('Tamanho do Vetor')
plt.ylabel('Número de Iterações')
plt.title('Número de Iterações em Função do Tamanho do Vetor (Merge Sort)')
plt.legend()
plt.grid(True)
plt.show()
