package com.ieslab.upyear;

/**
 * 2013年上半年设计试题实现.
 * 
 * @author 郭凯强
 */
public class Up2013 {

	public static void main(String[] args) {
		int m = 3, n = 7;
		int[] time = new int[] { 16, 14, 6, 5, 4, 3, 2 };
		int[] duration = new int[m];
		int[] count = new int[m];
		int[][] serialNo = new int[m][n];
		schedule(m, n, duration, serialNo, time, count);
	}

	/**
	 * 调度策略
	 * 
	 * @param m
	 *            机器数
	 * @param n
	 *            任务数
	 * @param duration
	 *            机器i的运行时间
	 * @param serialNo
	 *            机器i运行任务j的编号
	 * @param time
	 *            每个任务的运行时间
	 * @param count
	 *            机器i运行的任务数量
	 */
	private static void schedule(int m, int n, int[] duration, int[][] serialNo, int[] time, int[] count) {
		int i, j, k, max = 0;
		// 初始化
		for (i = 0; i < m; i++) {
			duration[i] = 0;
			for (j = 0; j < n; j++) {
				serialNo[i][j] = 0;
			}
		}
		// 分配前m个任务
		for (i = 0; i < m; i++) {
			serialNo[i][0] = i;
			duration[i] = time[i]; // gkq:
			count[i] = 1;
		}
		// 分配后n-m个任务
		for (i = m; i < n; i++) {
			int min = duration[0];
			k = 0; // 空闲机器编号
			// 确定空闲机器的编号
			for (j = 1; j < m; j++) {
				if (duration[j] < min) {
					min = duration[j];
					k = j;
				}
			}
			serialNo[k][count[k]] = i; // gkq:
			count[k] = count[k] + 1;
			duration[k] = duration[k] + time[i];
			// 确定完成所有任务所需要的时间
			for (int ii = 0; ii < m; ii++) {
				if (duration[ii] > max) { // gkq:
					max = duration[ii];
				}
			}
		}
		// 最后输出结果
		System.out.println("运行时长=" + max);
		for(int ii=0;ii!=serialNo.length;ii++){
			System.out.print("第"+(ii+1)+"台机器运行的编号=");
			if (ii==0) {
				System.out.println(0);
				continue;
			}
			for(int jj=0;jj!=serialNo[ii].length;jj++){
				if (serialNo[ii][jj]!=0) {
					System.out.print(serialNo[ii][jj]+",");
				}
			}
			System.out.println();
		}
	}
}
