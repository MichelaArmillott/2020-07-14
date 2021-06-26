package it.polito.tdp.PremierLeague.model;

import org.jgrapht.Graphs;


import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.*;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;



public class Model {
	private SimpleDirectedWeightedGraph<Team,DefaultWeightedEdge>grafo;
	private PremierLeagueDAO dao;
	private Map<Integer,Team>idMap;
	
	public Model() {
		dao=new PremierLeagueDAO();
		idMap=new HashMap<>();
		dao.listAllTeams(idMap);
	}
	
	public void creaGrafo() {
		grafo=new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		//vertici
		Graphs.addAllVertices(grafo,idMap.values());
		//archi
		this.calcolaPunti();
		for(Arco a: this.getArchi()) {
			DefaultWeightedEdge e=grafo.getEdge(a.getT1(), a.getT2());
			if(e==null) {
				if(a.getPeso()>0) {
					Graphs.addEdgeWithVertices(grafo, a.getT1(), a.getT2(), a.getPeso());}
				else {
					Graphs.addEdgeWithVertices(grafo, a.getT2(), a.getT1(), a.getPeso()*(-1));
				}
					
			}
		}
	}
	
	public int getNVertici() {
		if(grafo != null)
		return grafo.vertexSet().size();
		return 0;}
					
		public int getNArchi() {
		if(grafo != null)
		return grafo.edgeSet().size();
		return 0;}
		
	public void calcolaPunti() {
		for(Match m: dao.listAllMatches()) {
			if(m.resultOfTeamHome==1) {
				idMap.get(m.teamHomeID).puntiCampionato+=3;
			}
			else if(m.resultOfTeamHome==-1)
				idMap.get(m.teamAwayID).puntiCampionato+=3;
		    else {idMap.get(m.teamHomeID).puntiCampionato+=1;
		    idMap.get(m.teamAwayID).puntiCampionato+=1;
		    	
		    }}
	}
	public List<Arco> getArchi(){
		List<Arco>stampa=new ArrayList<>();
		
		for( Team t1:idMap.values()) {
			for(Team t2:idMap.values()) {
				if(!t1.getTeamID().equals(t2.getTeamID())) {
					double diff=t1.puntiCampionato-t2.puntiCampionato;
					if(diff!=0.0) {
						Arco a=new Arco(t1,t2,diff);
						stampa.add(a);
					}
				}
			}
		}
		return stampa;
	}
	// vertici per la comboBox
			public Set <Team>getCombo(){
				return grafo.vertexSet();
			}
			//verifico se esiste un grafo

	public SimpleDirectedWeightedGraph<Team,DefaultWeightedEdge>esisteGrafo(){
			return grafo;}
	
	public List<Arco> migliori(Team t){
		List<Arco>migliori=new ArrayList<>();
		for(DefaultWeightedEdge e:grafo.incomingEdgesOf(t)) {
			migliori.add(new Arco(grafo.getEdgeSource(e),grafo.getEdgeTarget(e),grafo.getEdgeWeight(e)));
		}
		Collections.sort(migliori);
		return migliori;
	}
	public List<Arco>peggiori(Team t){
		List<Arco>migliori=new ArrayList<>();
		for(DefaultWeightedEdge e:grafo.outgoingEdgesOf(t)) {
			migliori.add(new Arco(grafo.getEdgeSource(e),grafo.getEdgeTarget(e),grafo.getEdgeWeight(e)));
		}
		Collections.sort(migliori);
		return migliori;
	}
	}

