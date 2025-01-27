 class Transport {
    protected float speed;

    public Transport(float speed) {
        this.speed = speed;
    }

     public float travelTime(float distance) {
         return distance / speed;
     }
}
