#include <iostream>

using namespace std;

class Node{
public:
  int data;
  Node *next;
};

class LinkedList{
public:
  Node* head;
    LinkedList()
    {
        head = NULL;
    }
    void push(int newData){
      Node *newNode = new Node();
      newNode->data = newData;
      newNode->next = head;
      head = newNode;
    }

    void print(){
      Node * temp = head;
      while(temp!=NULL){
        cout<<temp->data<<endl;
        temp = temp->next;
      }
    }

    void reverse(){
      Node * current = head;
      Node * prev = NULL;
      Node * next = NULL;

      while(current!=NULL){
        next = current->next;
        current->next = prev;
        prev = current;
        current = next;
      }
      head = prev;
    }
};

int main(){
  LinkedList myLL;
  myLL.push(1);
  myLL.push(2);
  myLL.push(3);
  myLL.reverse();
  myLL.print();
  return 0;
}
