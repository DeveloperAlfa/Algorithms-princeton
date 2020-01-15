//winner winner candidate master
#include <bits/stdc++.h>
using namespace std;
#define int long long
#define FIO ios_base::sync_with_stdio(0); cin.tie(0);
#define vsort(v) sort(v.begin(), v.end());
#define endl '\n'
#define ff first
#define ss second
#define f(i, n) for(int i = 0; i < n; i++)
template <typename T> void vtrace(vector<T> v);
template <typename T> void trace(T count, ...);

//**********logic*************
/*
#Create a structure containing 2 stacks and dequeue from 2nd and enqueue in the first. Everytimre the second stack is empty perfrom a shift operation
*/
//**********code**************
class Queue
{
	stack<int> s1, s2;
public:
	void push(int x)
	{
		s1.push(x);
	}
	int front()
	{
		if(s2.empty()) shift();
		if(!s2.empty()) return s2.top();
		return -1;
	}
	void shift()
	{
		while(!s1.empty())
		{
			s2.push(s1.top());
			s1.pop();
		}
	}
	void pop()
	{
		if(s2.empty()) shift();
		if(!s2.empty()) s2.pop();
	}
};
signed main() {
    FIO;
    Queue q;
    q.push(1);
    q.push(2);
    q.push(3);
    cout<<q.front()<<endl;
    q.push(4);
    q.pop();
    q.pop();
    q.pop();
    cout<<q.front()<<endl;
}
//**********trace*************
template <typename T>
void vtrace(vector<T> v)
{
    for(T i: v) cout<<i<<" ";
    cout<<endl;
}
template <typename T>
void trace(T count, ...)
{
    va_list list;
    va_start(list, count);
    for(int i = 1; i <= count; i++) cout<<i<<" : "<<va_arg(list, int)<<" ";
    va_end(list);
}