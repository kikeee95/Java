package futarapp;



public enum EpuletTipus {
        Lakas("lakás"),
        Bolt("bolt"),
        Benzinkut("benzinkút"),
        Pizzeria("pizzéria");
        
        private String tipus;
        
        private EpuletTipus(String tipus){
            this.tipus = tipus;
        }
        
        public String getTipus(){
            return tipus;
        }
}
