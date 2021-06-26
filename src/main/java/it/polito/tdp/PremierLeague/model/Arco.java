package it.polito.tdp.PremierLeague.model;

public class Arco implements Comparable<Arco>{
	private Team t1;
	private Team t2;
	private Double peso;
	public Arco(Team t1, Team t2, double peso) {
		super();
		this.t1 = t1;
		this.t2 = t2;
		this.peso = peso;
	}
	public Team getT1() {
		return t1;
	}
	public void setT1(Team t1) {
		this.t1 = t1;
	}
	public Team getT2() {
		return t2;
	}
	public void setT2(Team t2) {
		this.t2 = t2;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	@Override
	public int compareTo(Arco other) {
		// TODO Auto-generated method stub
		return this.peso.compareTo(other.peso) ;
	}
	

}
