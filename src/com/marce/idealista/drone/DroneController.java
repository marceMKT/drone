package com.marce.idealista.drone;

import java.util.HashSet;
import java.util.Set;

public class DroneController{
	private enum Direction{
		IZQUIERDA, 
		DERECHA, 
		ARRIBA,
		ABAJO;
	}
	
	private Set<Long> urbanizationsId;
	private Long idOrigin;
	private int area;
	
	/**
	 * Method that obtain urbanization's id from a coordinate (unknown implementation)
	 * @param coordX coordinate X
	 * @param coordY coordinate Y
	 * @return Long
	 */
	public Long obtenerIdentificadorUrbanizacion(double coordX, double coordY){
		return null;
	}

	/**
	 * Method that obtain urbanization's id from an urbanitation's id and a direction (unknown implementation)
	 * @param idOrigin urbanization's id 
	 * @param direction direction to obtain adjacent
	 * @return Long
	 */
	public Long obtenerAdyacente(Long idOrigin, Direction direction){
		return null;
	}
	
	/**
	 * Method that obtains urbanizations's id from a coordinate and an area
	 * @param coordX the origin's coordinate X 
	 * @param coordY the origin's coordinate Y 
	 * @param area area from the origin
	 * @return
	 */
	public Set<Long> obtenerUrbanizaciones(final double coordX, final double coordY, final int area){
		this.urbanizationsId = new HashSet<Long>();
		this.area = area;
		this.idOrigin = this.obtenerIdentificadorUrbanizacion(coordX, coordY);
		this.processDirection(Direction.IZQUIERDA);
		this.processDirection(Direction.DERECHA);
		this.processDirection(Direction.ARRIBA);
		this.processDirection(Direction.ABAJO);
		return this.urbanizationsId;
	}
	
	private void processDirection(final Direction direction){
		Long idOriginPlusArea = this.getIdAdjacentFromDirection(direction, Boolean.FALSE);
		this.addUrbIdToList(idOriginPlusArea);
		switch (direction) {
		case IZQUIERDA: case DERECHA:
			this.getVertical(idOriginPlusArea);			
			break;
		case ARRIBA: case ABAJO:
			this.getHorizontal(idOriginPlusArea);
			break;
		default:
			break;
		}
	}
	
	private Long getIdAdjacentFromDirection(final Direction direction, final Boolean addToListWhileProcess){
		Long idOriginPlusArea = idOrigin;
		for(int i = 0; i < area; i++){
			idOriginPlusArea = this.obtenerAdyacente(idOriginPlusArea, direction);
			if (addToListWhileProcess){
				this.addUrbIdToList(idOriginPlusArea);
			}
		}
		return idOriginPlusArea;
	}

	private void addUrbIdToList(final Long idOriginPlusArea) {
		urbanizationsId.add(idOriginPlusArea);
	}
	
	private void getVertical(final Long idUrb) {
		this.getIdAdjacentFromDirection(Direction.ABAJO, Boolean.TRUE);
		this.getIdAdjacentFromDirection(Direction.ARRIBA, Boolean.TRUE);
	}

	private void getHorizontal(final Long idUrb) {
		this.getIdAdjacentFromDirection(Direction.IZQUIERDA, Boolean.TRUE);
		this.getIdAdjacentFromDirection(Direction.DERECHA, Boolean.TRUE);
	}
	
	
}