package it.polito.tdp.PremierLeague.model;

import java.util.Comparator;

public class Comparatore implements Comparator{
	public int compare(Object o1,Object o2) {
		Team t1=(Team)o1;
		Team t2=(Team)o2;
		if(t1.puntiCampionato<t2.puntiCampionato)
		return 1;
		else return -1;
	}

}
