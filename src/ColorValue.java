public class ColorValue {


        //we will store this as a button value
        private char value;

        public ColorValue(char sym) {
                if (!(sym == '^' || (sym >= 'a' && sym <= 'z'))) {
                        throw new IllegalArgumentException("Invalid color");
                }
                value = sym;
        }

        public static ColorValue fromIndex(int idx) {
                if( idx > 26 || idx < 0){
                        throw  new IllegalArgumentException("Color out of range 0-26");

                }

                if( idx == 0){
                        return new ColorValue('^');
                }
                //letter
                return new ColorValue((char) ('a' + idx - 1));
        }

        @Override
        public String toString() {
                return String.valueOf(value);
        }

        public char asButton() {
                return value;
        }

        public char asDoor(){
                return(char) (value - 'a' + 'A');
        }

        public int asIndex(){
                if(value == '^'){
                        return 0;
                }
                return value - 'a' + 1;
        }
}
