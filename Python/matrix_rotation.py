#matrix rotations

def clockwise_rotate(matrix):
    h = len(matrix)
    w = len(matrix[0])
    result = [[None for _ in range(h)] for _ in range(w)]
    for y in range(h):
        for x in range(w):
            result[x][y] = matrix[h-y-1][x]
    return result

def anticlockwise_rotate(matrix):
    return clockwise_rotate(clockwise_rotate(clockwise_rotate(matrix))) #cheat codes enabled

def matrix_print(matrix):
    for e in matrix:
        print(e)

def main():
    matrix = [
        [1,2,3,4],
        [5,6,7,8],
        [9,10,11,12]
    ]
    matrix_print(matrix)
    print('-'*10)
    matrix_print(clockwise_rotate(matrix))
    print('-'*10)
    matrix_print(anticlockwise_rotate(matrix))


if __name__ == '__main__':
    main()