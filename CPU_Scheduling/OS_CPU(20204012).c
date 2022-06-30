/*
    컴퓨터소프트웨어공학과 20204012 조혜진
    운영체제 CPU Scheduling
*/

#include <stdio.h>
#include <stdlib.h>
#include <windows.h>
#define MAXSIZE 1000
#pragma warning (disable:4996)

// ------사용할 구조체 선언-------------------------------------
typedef struct process {
    int pro_num, cpu_time, arr_t, pri, wait_t, ta_t, rem_t, res_t, TS;
}process;

// ------HRN 알고리즘 內 사용할 구조체 선언----------------------
typedef struct HRN_process {
    float cpu_time, pri, wait_t, ta_t, rem_t;
}HRN_process;

// ------------스케줄링 알고리즘 함수----------------------------
int process_fcfs(process* pro, int n);
int process_srt(process* pro, int n);
int process_hrn(process* pro, HRN_process* HRNpro, int n);
int process_pri(process* pro, int n);
int process_rr(process* pro, int n);
int process_sjf(process* pro, int n);

// -----------결과 창 출력 함수 ---------------------------------
void print(process* pro, int n);

// -----------프로세스를 정렬 함수 ------------------------------
void at_sort(process* pro, int n);
void resort(process* pro, int n);

//===============================================================
int main() {
    int i = 0;
    int n = 0;
    int index = 0;
    float tat;

    FILE* fp = fopen("proc.txt", "r");
    process ready_queue[MAXSIZE];
    HRN_process HRN_queue[MAXSIZE];

    // 파일 읽기
    while (!feof(fp)) {
        fscanf(fp, "%d", &ready_queue[i].pro_num);  // 프로세스 번호
        fscanf(fp, "%d", &ready_queue[i].arr_t);    // 도착시간
        fscanf(fp, "%d", &ready_queue[i].cpu_time); // CPU 사용시간
        fscanf(fp, "%d", &ready_queue[i].pri);      // 우선순위
        fscanf(fp, "%d", &ready_queue[i].TS);       // 타임 슬라이스
        ready_queue[i].rem_t = ready_queue[i].cpu_time; // 남은 시간
        index = index + 1;
        i++;
    }
    fclose(fp);     // 파일 닫기

    n = index - 1;
    printf("\n=================Main Menu====================\n1. Read processes from proc.txt\n2. First come first Serve (FCFS)\n3. Shortest Job First (SJF)\n4. Highest Response Ratio Next (HRN)\n5. Shortest Remaining Time first (SRT)\n6. Priority\n7. Round Robin (RR)\n8. Exit\n==============================================\n");

    while (1) {
        int menu = 0;
        printf("메뉴를 선택해주세요 >> ");
        scanf("%d", &menu);

        switch (menu) {
        case 1:         // read file
            printf("1. Read processes from proc.txt\n====================PROC======================\n");
            printf("PID\tAT\tBT\tPri\n");
            for (i = 0; i < n; i++)
                printf("%d\t%d\t%d\t%d\n", ready_queue[i].pro_num, ready_queue[i].arr_t, ready_queue[i].cpu_time, ready_queue[i].pri);
            printf("==============================================\n");
            continue;
            break;

        case 2:                 // FCFS            
            printf("2. First come first Serve (FCFS)\n====================FCFS======================\n");
            at_sort(ready_queue, n);
            process_fcfs(ready_queue, n);
            resort(ready_queue, n);

            print(ready_queue, n);
            continue;
            break;

        case 3:                  // SJF
            printf("3. Shortest Job First (SJF)\n====================SJF=======================\n");
            at_sort(ready_queue, n);
            process_sjf(ready_queue, n);
            resort(ready_queue, n);

            print(ready_queue, n);
            continue;
            break;

        case 4:                   // HRN
            printf("4. Highest Response Ratio Next (HRN)\n====================HRN=======================\n");
            at_sort(ready_queue, n);
            process_hrn(ready_queue, HRN_queue, n);
            resort(ready_queue, n);

            print(ready_queue, n);
            continue;
            break;

        case 5:                    // SRT
            printf("5. Shortest Remaining Time first (SRT)\n====================SRT=======================\n");
            at_sort(ready_queue, n);
            process_srt(ready_queue, n);
            resort(ready_queue, n);

            print(ready_queue, n);
            continue;
            break;

        case 6:                    // PRI
            printf("6. Priority\n====================PRI=======================\n");
            at_sort(ready_queue, n);
            process_pri(ready_queue, n);
            resort(ready_queue, n);

            print(ready_queue, n);
            continue;
            break;

        case 7:                  // RR
            printf("7. Round Robin (RR)\n====================RR========================\n");
            at_sort(ready_queue, n);
            process_rr(ready_queue, n);
            resort(ready_queue, n);

            print(ready_queue, n);
            continue;
            break;

        case 8:                 // exit
            printf("\n\n====================EXIT======================\n");
            exit(0);
            break;
        }
    }
}

// 도착한 시간 순으로 프로세스를 정렬하는 함수
void at_sort(process* pro, int n) {
    process temp;
    int i, j;

    for (i = n - 1; i > 0; i--) {
        for (j = 0; j < i; j++) {
            // i가 n-1부터 시작해서 0부터 n까지 process를 비교하고, 
            // 그 다음 i가 n-2 가 되면 다음 값부터 마지막까지 비교한다. 
            if (pro[j].arr_t > pro[j + 1].arr_t) {
                temp = pro[j + 1];
                pro[j + 1] = pro[j];
                pro[j] = temp;
            }
            else if (pro[j].arr_t == pro[j + 1].arr_t && pro[j].pro_num > pro[j + 1].pro_num) {
                // 동시 도착 시 프로세스 번호 순으로 정렬한다.
                temp = pro[j + 1];
                pro[j + 1] = pro[j];
                pro[j] = temp;
            }
        }
    }
}

// at_sort() 함수로 정렬된 프로세스를 원래 순서대로 재정렬하는 함수
void resort(process* pro, int n) {
    process temp;
    int i, j;

    for (i = n - 1; i > 0; i--) {
        for (j = 0; j < i; j++) {
            if (pro[j].pro_num > pro[j + 1].pro_num) {
                temp = pro[j + 1];
                pro[j + 1] = pro[j];
                pro[j] = temp;
            }
        }
    }
}

// 출력 함수
void print(process* pro, int n) {
    float tat = 0.0, awt = 0.0;
    // 대기 시간
    printf("ⓑ 프로세스별 대기 시간\n");
    for (int i = 0; i < n; i++) {
        awt += pro[i].wait_t;
        printf("[Pro%d] %dms\n", pro[i].pro_num, pro[i].wait_t);
    }
    printf("\nⓒ 평균 대기 시간 : %.2fms\n", awt / n);

    // 응답 시간
    printf("\nⓓ 프로세스별 응답 시간\n");
    for (int i = 0; i < n; i++) {
        printf("[Pro%d] %dms\n", pro[i].pro_num, pro[i].wait_t);
    }
    printf("\nⓔ 평균 대기 시간 : %.2fms\n", awt / n);

    // 반환 시간
    printf("\nⓕ 프로세스별 반환 시간\n");
    for (int i = 0; i < n; i++) {
        tat += (pro[i].cpu_time + pro[i].wait_t);
        printf("[Pro%d] %dms\n", pro[i].pro_num, pro[i].ta_t);
    }
    printf("\nⓖ 평균 반환 시간 : %.2fms\n", tat / n);   //  
    printf("==============================================\n\n");
}

// FCFS
int process_fcfs(process* pro, int n) {
    int temp;
    int wt = 0;
    int i, j, k = 0;
    int start[100], end[100];   // 간트 차트 변수

    for (i = 0; i < n; i++) {
        temp = 0;
        for (j = k; j < i; j++) {
            temp = temp + pro[j].cpu_time;
        }
        wt = temp - pro[i].arr_t + pro[k].arr_t;    // 대기시간

        // 먼저 도착한 프로세스 우선 처리
        if (wt <= 0) {
            k = i;
            pro[i].wait_t = 0;
            pro[i].ta_t = pro[i].cpu_time + pro[i].wait_t;
            wt = 0;
        }
        else {
            pro[i].wait_t = wt;
            pro[i].ta_t = pro[i].cpu_time + pro[i].wait_t;
            wt = 0;
        }
    }

    // 간트 차트
    printf("ⓐ 간트 차트\n");
    start[0] = 0;
    end[0] = pro[0].cpu_time;

    for (int i = 0; i < n; i++) {
        start[i + 1] = start[i] + pro[i].cpu_time;
        end[i] = pro[i].ta_t + pro[i].arr_t;
    }
    for (int i = 0; i < n; i++) {
        printf(" %d [Pro%d] %d ->", start[i], pro[i].pro_num, end[i]);
    }
    printf("\n\n");
}

// SRT
// 기본적으로 RR을 사용하지만, 프로세스 선택 시 작업시간이 제일 적은(SJF) 프로세스 선택
int process_srt(process* pro, int n) {
    int temp, temp2[150];
    int i, count, time, remain, min, flag = 0, num;
    int tt = 0;    //total time에 쓰레기 값이 들어가면 안된다.
    int start[100], end[100];   // 간트 차트 변수

    printf("ⓐ 간트 차트\n");
    start[0] = 0;
    end[0] = pro[0].cpu_time;

    time = pro[0].arr_t;
    remain = n;

    while (remain > 0) {
        min = 9999;

        for (i = 0; pro[i].arr_t <= time && i < n; i++) {
            if (pro[i].rem_t != 0 && pro[i].cpu_time < min && pro[i].cpu_time>0) {
                num = i;
                min = pro[i].cpu_time;
                flag = 1;
            }
        }

        if (flag == 1) {
            temp = pro[num].TS;     // 타임슬라이스 복사
            // 남아있는 작업시간이 타임슬라이스보다 크면
            if (pro[num].rem_t > pro[num].TS) {
                pro[num].rem_t = pro[num].rem_t - pro[num].TS;
            }

            // 남아있는 작업시간이 타임슬라이스보다 작거나 같으면
            else if (pro[num].rem_t <= pro[num].TS) {
                temp = pro[num].rem_t;
                pro[num].rem_t = 0;
                remain--;
            }
            time += pro[num].cpu_time;

            // 간트 차트
            start[i] = tt;
            tt += temp; // 프로세스 반환시간
            pro[num].ta_t = tt;
            end[i] = tt;
            printf(" %d [Pro%d] %d ->", start[i], num + 1, end[i]);
            pro[i].res_t = end[i];
        }
    }
    // 남은 시간과 대기 시간
    for (i = 0; i < n; i++) {
        pro[i].wait_t = pro[i].ta_t - pro[i].cpu_time - pro[i].arr_t;
        pro[i].ta_t = pro[i].wait_t + pro[i].cpu_time;
    }
    printf("\n\n");
}

// HRN
int process_hrn(process* pro, HRN_process* HRNpro, int n) {
    int flag = 0;
    int i, time, remain, num, min, temp[150];
    int start[100], end[100];   // 간트 차트 변수

    printf("ⓐ 간트 차트\n");
    start[0] = 0;
    end[0] = pro[0].cpu_time;

    // 새로운 기준의 우선순위에 따라서 배정하기 위해
    // HRN 구조체에 값을 복사한다.
    for (i = 0; i < n; i++) {
        HRNpro[i].cpu_time = pro[i].cpu_time;
        HRNpro[i].wait_t = pro[i].wait_t;
        HRNpro[i].ta_t = pro[i].ta_t;
        HRNpro[i].pri = pro[i].pri;
        HRNpro[i].rem_t = HRNpro[i].cpu_time;
    }

    time = pro[0].arr_t;
    remain = n;

    // 우선순위
    for (i = 0; i < n; i++) {
        HRNpro[i].pri = (pro[i].wait_t + pro[i].cpu_time) / pro[i].cpu_time;
    }

    if (temp[0] != pro[0].cpu_time) {
        for (i = 0; i < n; i++) {
            temp[i] = pro[i].rem_t;
        }
    }

    // 우선순위에 따라서 프로세스 선택
    while (remain > 0) {
        min = 9999;

        for (i = 0; pro[i].arr_t <= time && i < n; i++) {
            if (pro[i].rem_t != 0 && HRNpro[i].pri < min && pro[i].cpu_time>0) {
                num = i;
                min = HRNpro[i].pri;
                flag = 1;
            }
        }
        if (flag == 1) {
            pro[num].rem_t = 0;
            pro[num].wait_t = time - pro[num].arr_t;
            remain--;

            // 간트 차트
            start[num] = time;
            end[num] = time + pro[num].cpu_time;
            printf(" %d [Pro%d] %d ->", start[num], pro[num].pro_num, end[num]);
            time += pro[num].cpu_time;
        }
        else {
            time++;
        }
        flag = 0;
    }

    // 반환 시간
    for (i = 0; i < n; i++) {
        pro[i].ta_t = pro[i].wait_t + pro[i].cpu_time;
    }
    for (i = 0; i < n; i++) {
        pro[i].rem_t = temp[i];
    }
    printf("\n\n");
}

// PRI
int process_pri(process* pro, int n) {
    int flag = 0;
    int i, time, remain, num, min, temp[150];
    int start[100], end[100];   // 간트 차트 변수

    printf("ⓐ 간트 차트\n");
    start[0] = 0;
    end[0] = pro[0].cpu_time;

    for (i = 0; i < n; i++) {
        pro[i].rem_t = pro[i].cpu_time;
    }

    time = pro[0].arr_t;
    remain = n;

    if (temp[0] != pro[0].cpu_time) {
        for (i = 0; i < n; i++) {
            temp[i] = pro[i].rem_t;
        }
    }

    // 우선순위에 따라 프로세스 선택
    while (remain > 0) {
        min = 9999;

        for (i = 0; pro[i].arr_t <= time && i < n; i++) {
            if (pro[i].rem_t != 0 && pro[i].pri < min && pro[i].cpu_time>0) {
                num = i;
                min = pro[i].pri;
                flag = 1;
            }
        }
        if (flag == 1) {
            pro[num].rem_t = 0;
            pro[num].wait_t = time - pro[num].arr_t;
            remain--;

            // 간트 차트
            start[num] = time;
            end[num] = time + pro[num].cpu_time;
            printf(" %d [Pro%d] %d ->", start[num], pro[num].pro_num, end[num]);
            time += pro[num].cpu_time;
        }
        else {
            time++;
        }
        flag = 0;
    }

    // 반환 시간
    for (i = 0; i < n; i++) {
        pro[i].ta_t = pro[i].wait_t + pro[i].cpu_time;
    }

    for (i = 0; i < n; i++) {
        pro[i].rem_t = temp[i];
    }
    printf("\n\n");
}

// SJF
int process_sjf(process* pro, int n) {
    int flag = 0;
    int i, time, remain, num, min, temp[150];
    int start[100], end[100];   // 간트 차트 변수

    printf("ⓐ 간트 차트\n");
    start[0] = 0;
    end[0] = pro[0].cpu_time;

    for (i = 0; i < n; i++) {
        pro[i].rem_t = pro[i].cpu_time;
    }

    time = pro[0].arr_t;
    remain = n;

    if (temp[0] != pro[0].cpu_time) {
        for (i = 0; i < n; i++) {
            temp[i] = pro[i].rem_t;
        }
    }

    while (remain > 0) {
        min = 9999;

        // CPU 실행 시간이 가장 적은 프로세스 우선 선택
        for (i = 0; pro[i].arr_t <= time && i < n; i++) {
            if (pro[i].rem_t != 0 && pro[i].cpu_time < min && pro[i].cpu_time>0) {
                num = i;
                min = pro[i].cpu_time;
                flag = 1;
            }
        }
        if (flag == 1) {
            pro[num].rem_t = 0;
            pro[num].wait_t = time - pro[num].arr_t;
            remain--;

            // 간트 차트
            start[num] = time;
            end[num] = time + pro[num].cpu_time;
            printf(" %d [Pro%d] %d ->", start[num], pro[num].pro_num, end[num]);
            time += pro[num].cpu_time;
        }
        else {
            time++;
        }
        flag = 0;
    }

    // 반환 시간
    for (i = 0; i < n; i++) {
        pro[i].ta_t = pro[i].wait_t + pro[i].cpu_time;
    }
    for (i = 0; i < n; i++) {
        pro[i].rem_t = temp[i];
    }
    printf("\n\n");
}

// RR
int process_rr(process* pro, int n) {
    int temp, temp2[150];
    int i, count;
    int tt = 0;    //total time에 쓰레기 값이 들어가면 안된다.
    int start[100], end[100];   // 간트 차트 변수

    printf("ⓐ 간트 차트\n");
    start[0] = 0;
    end[0] = pro[0].cpu_time;

    if (temp2[0] != pro[0].cpu_time) {
        for (i = 0; i < n; i++) {
            temp2[i] = pro[i].rem_t;
        }
    }

    while (1) {
        for (i = 0, count = 0; i < n; i++) {
            temp = pro[i].TS;
            if (pro[i].rem_t == 0) {
                count++;
                continue;
            }
            // 남은 동작시간이 타임슬라이스보다 크다면
            if (pro[i].rem_t > pro[i].TS) {
                pro[i].rem_t = pro[i].rem_t - pro[i].TS;
            }

            else if (pro[i].rem_t >= 0) {
                temp = pro[i].rem_t;
                pro[i].rem_t = 0;
            }

            // 간트 차트
            start[i] = tt;
            tt = tt + temp; // 프로세스 반환시간
            pro[i].ta_t = tt - pro[i].arr_t;
            end[i] = tt;
            printf(" %d [Pro%d] %d ->", start[i], pro[i].pro_num, end[i]);
            pro[i].res_t = end[i];
        }
        if (n == count)
            break;
    }

    // 남은 시간과 대기 시간
    for (i = 0; i < n; i++) {
        pro[i].rem_t = temp2[i];
        pro[i].wait_t = pro[i].ta_t - pro[i].cpu_time;
    }
    printf("\n\n");
}