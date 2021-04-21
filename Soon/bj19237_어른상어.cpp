#include <iostream>

using namespace std;

int N, M, k;

struct SHARK {
	int x, y, dir;
	int priority[5][5];
};

struct M_DATA {
	int shark = 0,smell_num = 0, smell_time = -2000;
};

M_DATA map[20][20];
SHARK shark[402];

int dx[] = { 0,0,0,-1,1 };
int dy[] = { 0,-1,1,0,0 };

int main(void) {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> N >> M >> k;
	int cnt = M;

	int temp;
	for (int i = 0; i < N; i++)	{
		for (int j = 0; j < N; j++)	{
			cin >> temp;
			map[i][j].shark = temp;
			if (map[i][j].shark != 0) {
				shark[temp].x = j;
				shark[temp].y = i;
				map[i][j].smell_num = temp;
				map[i][j].smell_time = 0;
			}
		}
	}

	for (int i = 1; i <= M; i++) cin >> shark[i].dir;
	//up > down > left > right
	for (int i = 1; i <= M; i++)	{
		for (int j = 1; j <= 4; j++)		{
			for (int k = 1; k <= 4; k++)			{
				cin >> shark[i].priority[j][k];
			}
		}
	}

	//for (int i = 1; i <= M; i++) 
	//	cout << shark[i].x << "," << shark[i].y << " / ";

	int second = 0,d,nd,nx,ny,cx,cy;

	while (second++ <= 1000) {
		//ÀÌµ¿
		for (int i = 1; i <= M; i++){
			if (shark[i].x == -1) continue;
			
			cx = shark[i].x;
			cy = shark[i].y;
			d = shark[i].dir;
			//¾Æ¹« ³¿»õ°¡ ¾ø´Â Ä­
			for (int j = 1; j <= 4; j++)
			{
				nd = shark[i].priority[d][j];
				nx = cx + dx[nd];
				ny = cy + dy[nd];
				if (nx >= 0 && ny >= 0 && nx < N && ny < N &&
					map[ny][nx].smell_time < second - k) {
					shark[i].x = nx;
					shark[i].y = ny;
					shark[i].dir = nd;
					break;
				}
				//if(second - map[ny][nx].smell_time < k)
			}
			//ÀÚ½ÅÀÇ ³¿»õ°¡ ÀÖ´Â Ä­
			if (shark[i].x == cx && shark[i].y == cy) {
				for (int j = 1; j <= 4; j++)
				{
					nd = shark[i].priority[d][j];
					nx = cx + dx[nd];
					ny = cy + dy[nd];
					if (nx >= 0 && ny >= 0 && nx < N && ny < N &&
						map[ny][nx].smell_time >= second - k && 
						map[ny][nx].smell_num == i) {
						shark[i].x = nx;
						shark[i].y = ny;
						shark[i].dir = nd;
						break;
					}
				}
			}
			//»ó¾î ¾Æ¿ô!
			for (int j = 1; j < i; j++)	{
				if (shark[i].x == shark[j].x && shark[i].y == shark[j].y) {
					shark[i].x = -1; cnt--; break;
				}
			}
		}
		//1¹ø »ó¾î¸¸ ³²´Â°æ¿ì
		if (cnt == 1 && shark[1].x != -1) break;

		//³¿»õ »Ñ¸®±â
		for (int i = 1; i <= M; i++){
			//cout << shark[i].x << "," << shark[i].y << " / ";
			if (shark[i].x == -1) continue;
			map[shark[i].y][shark[i].x].smell_num = i;
			map[shark[i].y][shark[i].x].smell_time = second;
		}

		/*
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				cout << "(" << map[i][j].smell_num << "," << map[i][j].smell_time << ") ";

			}
			cout << endl;
		}

		cout << "---------------" << endl;
		*/
	}

	if (second > 1000) cout << -1;
	else cout << second;
}
