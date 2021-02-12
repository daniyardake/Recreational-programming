def f(x):
    if (x==1):
        return -0.5
    res = (3.0)/(f(x-1)+2)
    return res

for i in range(1,20):
    print(f(i))