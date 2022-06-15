package physique;

public class PosAnterieur {
 
    //positions precedentes
	//utile pour colision

    /** position x precedente */
	public double opx;

	/** position y precedente */
	public double opy;

    /** vitesse x precedente */
	public double ovx;
    
    /** vitesse y precedente */
	public double ovy;

	/** acceleration x precedente */
	public double oax;
	
	/** acceleration y precedente */
	public double oay;

    /** situation aerienne precedente */
    public boolean air;

    /**
     * Construit une position antérieure
     * @param opx
     * @param opy
     * @param ovx
     * @param ovy
     * @param oax
     * @param oay
     * @param air
     */
    public PosAnterieur(double opx, double opy, double ovx, double ovy, double oax, double oay, boolean air){
        this.opx = opx;
        this.opy = opy;
        this.ovx = ovx;
        this.ovy = ovy;
        this.oax = oax;
        this.oay = oay;
        this.air = air;
    }

}
