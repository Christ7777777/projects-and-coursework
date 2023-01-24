module bobs_circuit(w, x, y, z, b);
input w, x, y, z;
output b;

assign b = (x&~y&z)|(w&y&z)|(~w&~x&y&~z);
endmodule