public secundomer() {
int s = 0;
        int m = 0;
        int h = 0;
        for(int i = 0;i<1000000;i++) {
        s++;
        if (s == 60) {
            m++;
            s = 0;
        }
            if (m == 60) {
                h++;
                m = 0;
            }
        Thread.sleep(1000);
        print(h+":"+m+":"+s);
    }
    }
