# Write a function to partition a list into at most n chunks.  Return a list of
# lists of those chunks.  The difference between chunk sizes should never be 
# larger than 1.


def partition_list(l:list, n:int) -> list:
    # Suppose that a = n*k+r (r is remainder, so r<n)
    # Then a = n*k+r = r*k + (n-r)*k + r = r(k+1)+(n-r)k
    # Which means that we can combine them in r group of size k+1 and (n-r) groups of size k

    a = len(l) 
    r = (a % n)
    k = (a-r)//n

    
    return [ l[(k+1)*i:(k+1)*(i+1)] for i in range (r)] + [l[(k+1)*r+k*i: (k+1)*r+k*(i+1)] for i in range (n-r)]
