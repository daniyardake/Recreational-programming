# Write a class that accepts numbers one at a time and incrementally calculates
# the mean and sample standard deviation.  It should be able to work with a any
# size input including larger than available memory.


class IncrStats:
    def __init__(self):
        self.counter = 0
        self.sum = 0
        self.sq_diff = 0

    def add(self, x:float):
        old_mean = self.mean()
        self.sum += x
        self.counter += 1
        self.sq_diff+= ((x-old_mean)*(x-self.mean()))
        
        

    def mean(self) -> float:
        if (self.counter ==0):
            return 0
        return ((self.sum+0.0)/self.counter)

    def stdev(self) -> float:
        if (self.counter ==0 or self.counter == 1):
            return None
        return (self.sq_diff/(self.counter-1))**0.5
        