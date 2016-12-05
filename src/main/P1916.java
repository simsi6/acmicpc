package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class P1916 {
	// https://www.acmicpc.net/problem/1916
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int city = Integer.valueOf(in.readLine());
		int bus = Integer.valueOf(in.readLine());
				
		List<List<Pair>> adjacentList = new ArrayList<>();
		for(int i=0 ; i<=city ; i++) {
			adjacentList.add(new ArrayList<>());
		}
		
		for(int i=0 ; i<bus ; i++) {
			String[] str = in.readLine().split(" ");
			int from = Integer.valueOf(str[0]);
			int to = Integer.valueOf(str[1]);
			int cost = Integer.valueOf(str[2]);
			adjacentList.get(from).add(new Pair(to, cost));
		}
		
		boolean[] visited = new boolean[city+1];
		Arrays.fill(visited, false);
		
		int[] distance = new int[city+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		
		String[] str = in.readLine().split(" ");
		int start = Integer.valueOf(str[0]);
		int end = Integer.valueOf(str[1]);
		
		pq.add(new Pair(start, 0));
		distance[start] = 0;
		while(!pq.isEmpty()) {
			int curr;
			do {
				curr = pq.peek().to;
				pq.poll();
			} while(!pq.isEmpty() && visited[curr]);
			
			if(visited[curr]) {
				break; // 모든 정점 방문 - 종료
			} else {
				visited[curr] = true;
			}
			
			for(Pair pair : adjacentList.get(curr)) {
				if(distance[pair.to] > distance[curr] + pair.cost) {
					distance[pair.to] = distance[curr] + pair.cost;
					pq.add(new Pair(pair.to, distance[pair.to]));
				}
			}
		}
		
		System.out.print(distance[end]);
	}
	
	public static class Pair implements Comparable<Pair>{
		int to;
		int cost;
		
		private Pair(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Pair o) {
			// 오름차순
			if(this.cost > o.cost) {
				return 1;
			} else if (this.cost < o.cost) {
				return -1;
			} else {
				return 0;
			}
		}
	}
}