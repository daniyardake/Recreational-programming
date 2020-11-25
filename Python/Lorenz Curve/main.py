# Draw a Lorenz Curve for Normalized Data of Population

import numpy as np
from matplotlib import pyplot as plt
data = np.load('pop2010.npy')
data.sort()
#load and sort data
lorenz = data @ np.triu(np.ones( (data.shape[0],data.shape[0]), dtype = int), k=0) 
#multiply data by upper triangular matrix (with ones)
lorenz = lorenz / lorenz[-1]
#normalize data
x = np.arange(1, data.shape[0]+1)/data.shape[0]
#countries normalized
plt.title("Lorenz Curve for countries population contrubition") 
plt.xlabel("Number of countries normalized") 
plt.ylabel("Common population normalized") 
plt.plot(x,lorenz, "g")
plt.savefig('population-lorenz.png', dpi=200)