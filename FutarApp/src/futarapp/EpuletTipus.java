package futarapp;



public enum EpuletTipus {
        Lakas("lak�s"),
        Bolt("bolt"),
        Benzinkut("benzink�t"),
        Pizzeria("pizz�ria");
        
        private String tipus;
        
        private EpuletTipus(String tipus){
            this.tipus = tipus;
        }
        
        public String getTipus(){
            return tipus;
        }
}
