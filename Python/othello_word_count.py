"""
<-------------------------------------------------------------------------->
Shakespeare's plays traditionally have many words that are now either out of use or spelled differently
Write a program that displays the 25 most frequently used alphabetic words in Othello that are not in the corpus of the modern English words (the NLTK words).
Your program shall download (and cache, and read from the cache, if available) the file with urllib. 
The program then shall extract the text with BeautifulSoup, tokenize the text with NLTK,
lemmatize the words, and count the words that are not in the words corpus.
Remember that the words in the corpus are in the lower case.
@author: daniyar
<-------------------------------------------------------------------------->
"""
from nltk.corpus import words
from collections import Counter
from nltk.corpus import stopwords
from nltk.tokenize import word_tokenize
from heapq import nlargest
from nltk.stem import WordNetLemmatizer
import urllib.request
import os.path
from os import path
from bs4 import BeautifulSoup


def initialize(name, url):
    #downloads html file from http://shakespeare.mit.edu/othello/full.html (if wasn't downloaded before)
    #reads content of the file and returns text of the play
    if(path.exists(name)):
        print("HTML file already here. Processing Data...")
    else:
        urllib.request.urlretrieve(url, name)
    html = open(name).read()
    soup = BeautifulSoup(html, 'lxml')
    text = soup.text.lower()
    return text

def process(text):
    wordlist  = word_tokenize(text) #tokenized text
    wordlist = [word for word in wordlist if word not in set(stopwords.words('english')) and word.isalnum()] 
    lem = WordNetLemmatizer()   
    res = [lem.lemmatize(w, pos = "v") for w in wordlist]
    return res
    

def res_out(text):
    temp = list(set(text)-set(words.words("en")))
    #set of all words from othello that are not in words
    words_counted = dict()
    for w in temp:
        words_counted[w] = text.count(w)
    return words_counted
 
def main():
    url = 'http://shakespeare.mit.edu/othello/full.html'
    name = 'othello.html'
    text = initialize(name, url)
    processed_text = process(text)
    result = res_out(processed_text)
    res = nlargest(25, result, key = result.get) 
    for w in res:
        print(w + ":"+str(result[w]))

if __name__=='__main__':
    main()




