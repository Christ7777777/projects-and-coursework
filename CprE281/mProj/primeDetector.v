module primeDetector(w, x, y, z, p);
input w, x, y, z;
output p;

assign p = (x&~y&z)|(~w&x&z)|(~x&y&z)|(~w&~x&y);
endmodule
