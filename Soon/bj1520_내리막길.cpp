#include <iostream>

#define MAX 500
using namespace std;

int N, M, answer;
int map[MAX][MAX];
int dp[MAX][MAX];

int dx[] = { 0,0,1,-1 };
int dy[] = { 1,-1,0,0 };

int dfs(int x, int y) {
	if (dp[y][x] != -1) return dp[y][x];
	int nx, ny;

	dp[y][x] = 0;
	//4dir search
	for (int d = 0; d < 4; d++)
	{
		nx = x + dx[d];
		ny = y + dy[d];
		if (nx >= 0 && ny >= 0 && nx < M && ny < N) {
			if (map[y][x] < map[ny][nx]) {
				dp[y][x] += dfs(nx, ny);
			}
		}
	}
	return dp[y][x];
}

int main(void) {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> N >> M;
	for (int i = 0; i < N; i++)	{
		for (int j = 0; j < M; j++)	{
			cin >> map[i][j];
			dp[i][j] = -1;
		}
	}

	dp[0][0] = 1;

	
	cout << dfs(M - 1, N - 1) << endl;


	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cout << dp[i][j];
		}
		cout << endl;
	}
}

