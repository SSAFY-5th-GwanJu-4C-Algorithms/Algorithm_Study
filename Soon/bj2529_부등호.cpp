#include<iostream>

using namespace std;

int k;
bool sign[11] = { false };
bool visit[11] = { false };
int num[11] = { 0, };
__int64 t_num,max_num = 0, min_num = 9999999999;
//k = 9 일때 자리수 초과;;

void check() {
	int i;
	for (i = 0; i < k; i++)
	{
		if (sign[i] && num[i] >= num[i + 1]) break;
		else if (!sign[i] && num[i] <= num[i + 1]) break;
	}
	if (i >= k) {
		t_num = 0;
		//cout << num[9] <<"?";
		for (int j = 0; j <= k; j++)
		{
			t_num *= 10;
			t_num += num[j];
		//	cout << t_num << ",";
		}
		/*cout << "//";
		for (int i = 0; i <= k; i++)
		{
			cout <<num[i];
		}
		cout <<"/"<< t_num << endl;*/
		if (t_num > max_num) max_num = t_num;
		if (t_num < min_num) min_num = t_num;
	}
}

void solve(int idx) {
	if (idx > k) {
		check();
		return;
	}

	for (int i = 0; i < 10; i++)
	{
		if (!visit[i]) {
			visit[i] = true;
			num[idx] = i;
			solve(idx + 1);
			visit[i] = false;
		}
	}
}

int main(void) {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> k;

	char temp;
	for (int i = 0; i < k; i++)
	{
		cin >> temp;
		if (temp == '<') sign[i] = true;
	}

	solve(0);

	int a = 1;
	for (int i = 0; i < k; i++)
		a *= 10;

	if (max_num / a == 0) cout << 0 << max_num << endl;
	else cout << max_num << endl;
	if (min_num / a == 0) cout << 0 << min_num << endl;
	else cout << min_num << endl;

	return 0;
}