""" This program finds number of primes from 1 to n """
import sys

def eratosthenes_sieve(n): 
    prime = [True for _ in range(n + 1)] 
    p = 2
    while (p * p <= n):           
        if (prime[p]): 
            for i in range(2*p, n + 1, p): 
                prime[i] = False
        p += 1
    prime[0]= False
    prime[1]= False
    
    return sum(prime)
    

def main():
    arguments = sys.argv
    num = input('What is the number n: ') if len(arguments) < 2 else arguments[1]
    try:
        num = int(num)
    except:
        print('Invalid data')
        main()
    print('There are {} prime numbers from 1 to {}'.format( eratosthenes_sieve(num),num))

    

if __name__ == '__main__':
    main()