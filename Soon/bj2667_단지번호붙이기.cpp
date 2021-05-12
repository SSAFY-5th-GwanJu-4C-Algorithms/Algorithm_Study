#include<iostream>

using namespace std;

int N,push,pop;
int map[30][30];
int q[1000][2];
int x, y, nx, ny, idx;
int dx[] = { 0,0,1,-1 };
int dy[] = { 1,-1,0,0 };
int ans[1000];

int main(void) {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> N;

	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++) {
			char temp;
			cin >> temp;
			map[i][j] = temp - '0';
		}
		//	cin >> map[i][j];
	
	bool flag = true;
	push = 0, pop = 0, idx = 0;

	while (flag) {
		bool flag2 = false;
		int i;

		for (i = 0; i < N; i++) {
			if (flag2) break;
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1) {
					q[push][0] = i;
					q[push++][1] = j;
					map[i][j] = 0;
					int cnt = 0;
					
					while (push != pop) {
						y = q[pop][0];
						x = q[pop++][1];
						cnt++;

						for (int k = 0; k < 4; k++)
						{
							nx = x + dx[k];
							ny = y + dy[k];

							if (nx >= 0 && ny >= 0 && nx < N && ny < N && map[ny][nx] == 1) {
								q[push][0] = ny;
								q[push++][1] = nx;
								map[ny][nx] = 0;
							}
						}

					}
					
					ans[idx++] = cnt;

					flag2 = true;
				}
			}
		}

		if (i >= N && !flag2) flag = false;
	}

	cout << idx << endl;

	for (int i = 0; i < idx; i++)
	{
		for (int j = i; j < idx; j++)
		{
			if (ans[i] > ans[j]) {
				int a = ans[j];
				ans[j] = ans[i];
				ans[i] = a;
			}
		}
	}

	for (int i = 0; i < idx; i++)
	{
		cout << ans[i] << endl;
	}
	
	return 0;
}