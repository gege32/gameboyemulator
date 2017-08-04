package hu.fun.gameboyemulator.core;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CPU {
	
	private Clock clock;
	
	private int A, B, C, D, E, F, H, L, SP, PC;
	
	private boolean zero, substract, halfcarry, carry;
	
	private boolean halt;
	
	private MemoryBus membus;
	
	private void tick() {
		
	}
	
	public CPU(MemoryBus membus) {
		clock = new Clock();
		PC = 0x0100;
		this.membus = membus;
	}

	private void parseCommand(int command) {
		switch (command) {
		case 0x0: // mnemonic":"NOP","operands":[],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x1: // mnemonic":"LD","operands":["BC","d16"],"bytes":3,"cycles":12,"flagsZNHC":["-","-","-","-"]}
		case 0x2: // mnemonic":"LD","operands":["(BC)","A"],"bytes":1,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0x3: // mnemonic":"INC","operands":["BC"],"bytes":1,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0x4: // mnemonic":"INC","operands":["B"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","H","-"]}
		case 0x5: // mnemonic":"DEC","operands":["B"],"bytes":1,"cycles":4,"flagsZNHC":["Z","1","H","-"]}
		case 0x6: // mnemonic":"LD","operands":["B","d8"],"bytes":2,"cycles":8,"flagsZNHC":["-","-","-","-"]}
			
		case 0x7: // mnemonic":"RLCA","operands":[],"bytes":1,"cycles":4,"flagsZNHC":["0","0","0","C"]}
		case 0x8: // mnemonic":"LD","operands":["(a16)","SP"],"bytes":3,"cycles":20,"flagsZNHC":["-","-","-","-"]}
		case 0x9: // mnemonic":"ADD","operands":["HL","BC"],"bytes":1,"cycles":8,"flagsZNHC":["-","0","H","C"]}
		case 0xa: // mnemonic":"LD","operands":["A","(BC)"],"bytes":1,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0xb: // mnemonic":"DEC","operands":["BC"],"bytes":1,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0xc: // mnemonic":"INC","operands":["C"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","H","-"]}
		case 0xd: // mnemonic":"DEC","operands":["C"],"bytes":1,"cycles":4,"flagsZNHC":["Z","1","H","-"]}
		case 0xe: // mnemonic":"LD","operands":["C","d8"],"bytes":2,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0xf: // mnemonic":"RRCA","operands":[],"bytes":1,"cycles":4,"flagsZNHC":["0","0","0","C"]}
		case 0x10: // mnemonic":"STOP","operands":["0"],"bytes":2,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x11: // mnemonic":"LD","operands":["DE","d16"],"bytes":3,"cycles":12,"flagsZNHC":["-","-","-","-"]}
		case 0x12: // mnemonic":"LD","operands":["(DE)","A"],"bytes":1,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0x13: // mnemonic":"INC","operands":["DE"],"bytes":1,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0x14: // mnemonic":"INC","operands":["D"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","H","-"]}
		case 0x15: // mnemonic":"DEC","operands":["D"],"bytes":1,"cycles":4,"flagsZNHC":["Z","1","H","-"]}
		case 0x16: // mnemonic":"LD","operands":["D","d8"],"bytes":2,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0x17: // mnemonic":"RLA","operands":[],"bytes":1,"cycles":4,"flagsZNHC":["0","0","0","C"]}
		case 0x18: // mnemonic":"JR","operands":["r8"],"bytes":2,"cycles":12,"flagsZNHC":["-","-","-","-"]}
		case 0x19: // mnemonic":"ADD","operands":["HL","DE"],"bytes":1,"cycles":8,"flagsZNHC":["-","0","H","C"]}
		case 0x1a: // mnemonic":"LD","operands":["A","(DE)"],"bytes":1,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0x1b: // mnemonic":"DEC","operands":["DE"],"bytes":1,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0x1c: // mnemonic":"INC","operands":["E"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","H","-"]}
		case 0x1d: // mnemonic":"DEC","operands":["E"],"bytes":1,"cycles":4,"flagsZNHC":["Z","1","H","-"]}
		case 0x1e: // mnemonic":"LD","operands":["E","d8"],"bytes":2,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0x1f: // mnemonic":"RRA","operands":[],"bytes":1,"cycles":4,"flagsZNHC":["0","0","0","C"]}
		case 0x20: // mnemonic":"JR","operands":["NZ","r8"],"bytes":2,"cycles":12,"flagsZNHC":["-","-","-","-"]}
		case 0x21: // mnemonic":"LD","operands":["HL","d16"],"bytes":3,"cycles":12,"flagsZNHC":["-","-","-","-"]}
		case 0x22: // mnemonic":"LD","operands":["(HL+)","A"],"bytes":1,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0x23: // mnemonic":"INC","operands":["HL"],"bytes":1,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0x24: // mnemonic":"INC","operands":["H"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","H","-"]}
		case 0x25: // mnemonic":"DEC","operands":["H"],"bytes":1,"cycles":4,"flagsZNHC":["Z","1","H","-"]}
		case 0x26: // mnemonic":"LD","operands":["H","d8"],"bytes":2,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0x27: // mnemonic":"DAA","operands":[],"bytes":1,"cycles":4,"flagsZNHC":["Z","-","0","C"]}
		case 0x28: // mnemonic":"JR","operands":["Z","r8"],"bytes":2,"cycles":12,"flagsZNHC":["-","-","-","-"]}
		case 0x29: // mnemonic":"ADD","operands":["HL","HL"],"bytes":1,"cycles":8,"flagsZNHC":["-","0","H","C"]}
		case 0x2a: // mnemonic":"LD","operands":["A","(HL+)"],"bytes":1,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0x2b: // mnemonic":"DEC","operands":["HL"],"bytes":1,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0x2c: // mnemonic":"INC","operands":["L"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","H","-"]}
		case 0x2d: // mnemonic":"DEC","operands":["L"],"bytes":1,"cycles":4,"flagsZNHC":["Z","1","H","-"]}
		case 0x2e: // mnemonic":"LD","operands":["L","d8"],"bytes":2,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0x2f: // mnemonic":"CPL","operands":[],"bytes":1,"cycles":4,"flagsZNHC":["-","1","1","-"]}
		case 0x30: // mnemonic":"JR","operands":["NC","r8"],"bytes":2,"cycles":12,"flagsZNHC":["-","-","-","-"]}
		case 0x31: // mnemonic":"LD","operands":["SP","d16"],"bytes":3,"cycles":12,"flagsZNHC":["-","-","-","-"]}
		case 0x32: // mnemonic":"LD","operands":["(HL-)","A"],"bytes":1,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0x33: // mnemonic":"INC","operands":["SP"],"bytes":1,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0x34: // mnemonic":"INC","operands":["(HL)"],"bytes":1,"cycles":12,"flagsZNHC":["Z","0","H","-"]}
		case 0x35: // mnemonic":"DEC","operands":["(HL)"],"bytes":1,"cycles":12,"flagsZNHC":["Z","1","H","-"]}
		case 0x36: // mnemonic":"LD","operands":["(HL)","d8"],"bytes":2,"cycles":12,"flagsZNHC":["-","-","-","-"]}
		case 0x37: // mnemonic":"SCF","operands":[],"bytes":1,"cycles":4,"flagsZNHC":["-","0","0","1"]}
		case 0x38: // mnemonic":"JR","operands":["C","r8"],"bytes":2,"cycles":12,"flagsZNHC":["-","-","-","-"]}
		case 0x39: // mnemonic":"ADD","operands":["HL","SP"],"bytes":1,"cycles":8,"flagsZNHC":["-","0","H","C"]}
		case 0x3a: // mnemonic":"LD","operands":["A","(HL-)"],"bytes":1,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0x3b: // mnemonic":"DEC","operands":["SP"],"bytes":1,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0x3c: // mnemonic":"INC","operands":["A"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","H","-"]}
		case 0x3d: // mnemonic":"DEC","operands":["A"],"bytes":1,"cycles":4,"flagsZNHC":["Z","1","H","-"]}
		case 0x3e: // mnemonic":"LD","operands":["A","d8"],"bytes":2,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0x3f: // mnemonic":"CCF","operands":[],"bytes":1,"cycles":4,"flagsZNHC":["-","0","0","C"]}
		case 0x40: // mnemonic":"LD","operands":["B","B"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x41: // mnemonic":"LD","operands":["B","C"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x42: // mnemonic":"LD","operands":["B","D"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x43: // mnemonic":"LD","operands":["B","E"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x44: // mnemonic":"LD","operands":["B","H"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x45: // mnemonic":"LD","operands":["B","L"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x46: // mnemonic":"LD","operands":["B","(HL)"],"bytes":1,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0x47: // mnemonic":"LD","operands":["B","A"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x48: // mnemonic":"LD","operands":["C","B"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x49: // mnemonic":"LD","operands":["C","C"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x4a: // mnemonic":"LD","operands":["C","D"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x4b: // mnemonic":"LD","operands":["C","E"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x4c: // mnemonic":"LD","operands":["C","H"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x4d: // mnemonic":"LD","operands":["C","L"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x4e: // mnemonic":"LD","operands":["C","(HL)"],"bytes":1,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0x4f: // mnemonic":"LD","operands":["C","A"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x50: // mnemonic":"LD","operands":["D","B"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x51: // mnemonic":"LD","operands":["D","C"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x52: // mnemonic":"LD","operands":["D","D"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x53: // mnemonic":"LD","operands":["D","E"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x54: // mnemonic":"LD","operands":["D","H"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x55: // mnemonic":"LD","operands":["D","L"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x56: // mnemonic":"LD","operands":["D","(HL)"],"bytes":1,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0x57: // mnemonic":"LD","operands":["D","A"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x58: // mnemonic":"LD","operands":["E","B"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x59: // mnemonic":"LD","operands":["E","C"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x5a: // mnemonic":"LD","operands":["E","D"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x5b: // mnemonic":"LD","operands":["E","E"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x5c: // mnemonic":"LD","operands":["E","H"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x5d: // mnemonic":"LD","operands":["E","L"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x5e: // mnemonic":"LD","operands":["E","(HL)"],"bytes":1,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0x5f: // mnemonic":"LD","operands":["E","A"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x60: // mnemonic":"LD","operands":["H","B"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x61: // mnemonic":"LD","operands":["H","C"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x62: // mnemonic":"LD","operands":["H","D"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x63: // mnemonic":"LD","operands":["H","E"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x64: // mnemonic":"LD","operands":["H","H"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x65: // mnemonic":"LD","operands":["H","L"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x66: // mnemonic":"LD","operands":["H","(HL)"],"bytes":1,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0x67: // mnemonic":"LD","operands":["H","A"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x68: // mnemonic":"LD","operands":["L","B"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x69: // mnemonic":"LD","operands":["L","C"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x6a: // mnemonic":"LD","operands":["L","D"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x6b: // mnemonic":"LD","operands":["L","E"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x6c: // mnemonic":"LD","operands":["L","H"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x6d: // mnemonic":"LD","operands":["L","L"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x6e: // mnemonic":"LD","operands":["L","(HL)"],"bytes":1,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0x6f: // mnemonic":"LD","operands":["L","A"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x70: // mnemonic":"LD","operands":["(HL)","B"],"bytes":1,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0x71: // mnemonic":"LD","operands":["(HL)","C"],"bytes":1,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0x72: // mnemonic":"LD","operands":["(HL)","D"],"bytes":1,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0x73: // mnemonic":"LD","operands":["(HL)","E"],"bytes":1,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0x74: // mnemonic":"LD","operands":["(HL)","H"],"bytes":1,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0x75: // mnemonic":"LD","operands":["(HL)","L"],"bytes":1,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0x76: // mnemonic":"HALT","operands":[],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x77: // mnemonic":"LD","operands":["(HL)","A"],"bytes":1,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0x78: // mnemonic":"LD","operands":["A","B"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x79: // mnemonic":"LD","operands":["A","C"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x7a: // mnemonic":"LD","operands":["A","D"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x7b: // mnemonic":"LD","operands":["A","E"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x7c: // mnemonic":"LD","operands":["A","H"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x7d: // mnemonic":"LD","operands":["A","L"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x7e: // mnemonic":"LD","operands":["A","(HL)"],"bytes":1,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0x7f: // mnemonic":"LD","operands":["A","A"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0x80: // mnemonic":"ADD","operands":["A","B"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","H","C"]}
		case 0x81: // mnemonic":"ADD","operands":["A","C"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","H","C"]}
		case 0x82: // mnemonic":"ADD","operands":["A","D"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","H","C"]}
		case 0x83: // mnemonic":"ADD","operands":["A","E"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","H","C"]}
		case 0x84: // mnemonic":"ADD","operands":["A","H"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","H","C"]}
		case 0x85: // mnemonic":"ADD","operands":["A","L"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","H","C"]}
		case 0x86: // mnemonic":"ADD","operands":["A","(HL)"],"bytes":1,"cycles":8,"flagsZNHC":["Z","0","H","C"]}
		case 0x87: // mnemonic":"ADD","operands":["A","A"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","H","C"]}
		case 0x88: // mnemonic":"ADC","operands":["A","B"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","H","C"]}
		case 0x89: // mnemonic":"ADC","operands":["A","C"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","H","C"]}
		case 0x8a: // mnemonic":"ADC","operands":["A","D"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","H","C"]}
		case 0x8b: // mnemonic":"ADC","operands":["A","E"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","H","C"]}
		case 0x8c: // mnemonic":"ADC","operands":["A","H"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","H","C"]}
		case 0x8d: // mnemonic":"ADC","operands":["A","L"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","H","C"]}
		case 0x8e: // mnemonic":"ADC","operands":["A","(HL)"],"bytes":1,"cycles":8,"flagsZNHC":["Z","0","H","C"]}
		case 0x8f: // mnemonic":"ADC","operands":["A","A"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","H","C"]}
		case 0x90: // mnemonic":"SUB","operands":["B"],"bytes":1,"cycles":4,"flagsZNHC":["Z","1","H","C"]}
		case 0x91: // mnemonic":"SUB","operands":["C"],"bytes":1,"cycles":4,"flagsZNHC":["Z","1","H","C"]}
		case 0x92: // mnemonic":"SUB","operands":["D"],"bytes":1,"cycles":4,"flagsZNHC":["Z","1","H","C"]}
		case 0x93: // mnemonic":"SUB","operands":["E"],"bytes":1,"cycles":4,"flagsZNHC":["Z","1","H","C"]}
		case 0x94: // mnemonic":"SUB","operands":["H"],"bytes":1,"cycles":4,"flagsZNHC":["Z","1","H","C"]}
		case 0x95: // mnemonic":"SUB","operands":["L"],"bytes":1,"cycles":4,"flagsZNHC":["Z","1","H","C"]}
		case 0x96: // mnemonic":"SUB","operands":["(HL)"],"bytes":1,"cycles":8,"flagsZNHC":["Z","1","H","C"]}
		case 0x97: // mnemonic":"SUB","operands":["A"],"bytes":1,"cycles":4,"flagsZNHC":["Z","1","H","C"]}
		case 0x98: // mnemonic":"SBC","operands":["A","B"],"bytes":1,"cycles":4,"flagsZNHC":["Z","1","H","C"]}
		case 0x99: // mnemonic":"SBC","operands":["A","C"],"bytes":1,"cycles":4,"flagsZNHC":["Z","1","H","C"]}
		case 0x9a: // mnemonic":"SBC","operands":["A","D"],"bytes":1,"cycles":4,"flagsZNHC":["Z","1","H","C"]}
		case 0x9b: // mnemonic":"SBC","operands":["A","E"],"bytes":1,"cycles":4,"flagsZNHC":["Z","1","H","C"]}
		case 0x9c: // mnemonic":"SBC","operands":["A","H"],"bytes":1,"cycles":4,"flagsZNHC":["Z","1","H","C"]}
		case 0x9d: // mnemonic":"SBC","operands":["A","L"],"bytes":1,"cycles":4,"flagsZNHC":["Z","1","H","C"]}
		case 0x9e: // mnemonic":"SBC","operands":["A","(HL)"],"bytes":1,"cycles":8,"flagsZNHC":["Z","1","H","C"]}
		case 0x9f: // mnemonic":"SBC","operands":["A","A"],"bytes":1,"cycles":4,"flagsZNHC":["Z","1","H","C"]}
		case 0xa0: // mnemonic":"AND","operands":["B"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","1","0"]}
		case 0xa1: // mnemonic":"AND","operands":["C"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","1","0"]}
		case 0xa2: // mnemonic":"AND","operands":["D"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","1","0"]}
		case 0xa3: // mnemonic":"AND","operands":["E"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","1","0"]}
		case 0xa4: // mnemonic":"AND","operands":["H"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","1","0"]}
		case 0xa5: // mnemonic":"AND","operands":["L"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","1","0"]}
		case 0xa6: // mnemonic":"AND","operands":["(HL)"],"bytes":1,"cycles":8,"flagsZNHC":["Z","0","1","0"]}
		case 0xa7: // mnemonic":"AND","operands":["A"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","1","0"]}
		case 0xa8: // mnemonic":"XOR","operands":["B"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","0","0"]}
		case 0xa9: // mnemonic":"XOR","operands":["C"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","0","0"]}
		case 0xaa: // mnemonic":"XOR","operands":["D"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","0","0"]}
		case 0xab: // mnemonic":"XOR","operands":["E"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","0","0"]}
		case 0xac: // mnemonic":"XOR","operands":["H"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","0","0"]}
		case 0xad: // mnemonic":"XOR","operands":["L"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","0","0"]}
		case 0xae: // mnemonic":"XOR","operands":["(HL)"],"bytes":1,"cycles":8,"flagsZNHC":["Z","0","0","0"]}
		case 0xaf: // mnemonic":"XOR","operands":["A"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","0","0"]}
		case 0xb0: // mnemonic":"OR","operands":["B"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","0","0"]}
		case 0xb1: // mnemonic":"OR","operands":["C"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","0","0"]}
		case 0xb2: // mnemonic":"OR","operands":["D"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","0","0"]}
		case 0xb3: // mnemonic":"OR","operands":["E"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","0","0"]}
		case 0xb4: // mnemonic":"OR","operands":["H"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","0","0"]}
		case 0xb5: // mnemonic":"OR","operands":["L"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","0","0"]}
		case 0xb6: // mnemonic":"OR","operands":["(HL)"],"bytes":1,"cycles":8,"flagsZNHC":["Z","0","0","0"]}
		case 0xb7: // mnemonic":"OR","operands":["A"],"bytes":1,"cycles":4,"flagsZNHC":["Z","0","0","0"]}
		case 0xb8: // mnemonic":"CP","operands":["B"],"bytes":1,"cycles":4,"flagsZNHC":["Z","1","H","C"]}
		case 0xb9: // mnemonic":"CP","operands":["C"],"bytes":1,"cycles":4,"flagsZNHC":["Z","1","H","C"]}
		case 0xba: // mnemonic":"CP","operands":["D"],"bytes":1,"cycles":4,"flagsZNHC":["Z","1","H","C"]}
		case 0xbb: // mnemonic":"CP","operands":["E"],"bytes":1,"cycles":4,"flagsZNHC":["Z","1","H","C"]}
		case 0xbc: // mnemonic":"CP","operands":["H"],"bytes":1,"cycles":4,"flagsZNHC":["Z","1","H","C"]}
		case 0xbd: // mnemonic":"CP","operands":["L"],"bytes":1,"cycles":4,"flagsZNHC":["Z","1","H","C"]}
		case 0xbe: // mnemonic":"CP","operands":["(HL)"],"bytes":1,"cycles":8,"flagsZNHC":["Z","1","H","C"]}
		case 0xbf: // mnemonic":"CP","operands":["A"],"bytes":1,"cycles":4,"flagsZNHC":["Z","1","H","C"]}
		case 0xc0: // mnemonic":"RET","operands":["NZ"],"bytes":1,"cycles":20,"flagsZNHC":["-","-","-","-"]}
		case 0xc1: // mnemonic":"POP","operands":["BC"],"bytes":1,"cycles":12,"flagsZNHC":["-","-","-","-"]}
		case 0xc2: // mnemonic":"JP","operands":["NZ","a16"],"bytes":3,"cycles":16,"flagsZNHC":["-","-","-","-"]}
		case 0xc3: // mnemonic":"JP","operands":["a16"],"bytes":3,"cycles":16,"flagsZNHC":["-","-","-","-"]}
		case 0xc4: // mnemonic":"CALL","operands":["NZ","a16"],"bytes":3,"cycles":24,"flagsZNHC":["-","-","-","-"]}
		case 0xc5: // mnemonic":"PUSH","operands":["BC"],"bytes":1,"cycles":16,"flagsZNHC":["-","-","-","-"]}
		case 0xc6: // mnemonic":"ADD","operands":["A","d8"],"bytes":2,"cycles":8,"flagsZNHC":["Z","0","H","C"]}
		case 0xc7: // mnemonic":"RST","operands":["00H"],"bytes":1,"cycles":16,"flagsZNHC":["-","-","-","-"]}
		case 0xc8: // mnemonic":"RET","operands":["Z"],"bytes":1,"cycles":20,"flagsZNHC":["-","-","-","-"]}
		case 0xc9: // mnemonic":"RET","operands":[],"bytes":1,"cycles":16,"flagsZNHC":["-","-","-","-"]}
		case 0xca: // mnemonic":"JP","operands":["Z","a16"],"bytes":3,"cycles":16,"flagsZNHC":["-","-","-","-"]}
		case 0xcb: // mnemonic":"PREFIX","operands":["CB"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0xcc: // mnemonic":"CALL","operands":["Z","a16"],"bytes":3,"cycles":24,"flagsZNHC":["-","-","-","-"]}
		case 0xcd: // mnemonic":"CALL","operands":["a16"],"bytes":3,"cycles":24,"flagsZNHC":["-","-","-","-"]}
		case 0xce: // mnemonic":"ADC","operands":["A","d8"],"bytes":2,"cycles":8,"flagsZNHC":["Z","0","H","C"]}
		case 0xcf: // mnemonic":"RST","operands":["08H"],"bytes":1,"cycles":16,"flagsZNHC":["-","-","-","-"]}
		case 0xd0: // mnemonic":"RET","operands":["NC"],"bytes":1,"cycles":20,"flagsZNHC":["-","-","-","-"]}
		case 0xd1: // mnemonic":"POP","operands":["DE"],"bytes":1,"cycles":12,"flagsZNHC":["-","-","-","-"]}
		case 0xd2: // mnemonic":"JP","operands":["NC","a16"],"bytes":3,"cycles":16,"flagsZNHC":["-","-","-","-"]}
		case 0xd4: // mnemonic":"CALL","operands":["NC","a16"],"bytes":3,"cycles":24,"flagsZNHC":["-","-","-","-"]}
		case 0xd5: // mnemonic":"PUSH","operands":["DE"],"bytes":1,"cycles":16,"flagsZNHC":["-","-","-","-"]}
		case 0xd6: // mnemonic":"SUB","operands":["d8"],"bytes":2,"cycles":8,"flagsZNHC":["Z","1","H","C"]}
		case 0xd7: // mnemonic":"RST","operands":["10H"],"bytes":1,"cycles":16,"flagsZNHC":["-","-","-","-"]}
		case 0xd8: // mnemonic":"RET","operands":["C"],"bytes":1,"cycles":20,"flagsZNHC":["-","-","-","-"]}
		case 0xd9: // mnemonic":"RETI","operands":[],"bytes":1,"cycles":16,"flagsZNHC":["-","-","-","-"]}
		case 0xda: // mnemonic":"JP","operands":["C","a16"],"bytes":3,"cycles":16,"flagsZNHC":["-","-","-","-"]}
		case 0xdc: // mnemonic":"CALL","operands":["C","a16"],"bytes":3,"cycles":24,"flagsZNHC":["-","-","-","-"]}
		case 0xde: // mnemonic":"SBC","operands":["A","d8"],"bytes":2,"cycles":8,"flagsZNHC":["Z","1","H","C"]}
		case 0xdf: // mnemonic":"RST","operands":["18H"],"bytes":1,"cycles":16,"flagsZNHC":["-","-","-","-"]}
		case 0xe0: // mnemonic":"LDH","operands":["(a8)","A"],"bytes":2,"cycles":12,"flagsZNHC":["-","-","-","-"]}
		case 0xe1: // mnemonic":"POP","operands":["HL"],"bytes":1,"cycles":12,"flagsZNHC":["-","-","-","-"]}
		case 0xe2: // mnemonic":"LD","operands":["(C)","A"],"bytes":2,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0xe5: // mnemonic":"PUSH","operands":["HL"],"bytes":1,"cycles":16,"flagsZNHC":["-","-","-","-"]}
		case 0xe6: // mnemonic":"AND","operands":["d8"],"bytes":2,"cycles":8,"flagsZNHC":["Z","0","1","0"]}
		case 0xe7: // mnemonic":"RST","operands":["20H"],"bytes":1,"cycles":16,"flagsZNHC":["-","-","-","-"]}
		case 0xe8: // mnemonic":"ADD","operands":["SP","r8"],"bytes":2,"cycles":16,"flagsZNHC":["0","0","H","C"]}
		case 0xe9: // mnemonic":"JP","operands":["(HL)"],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0xea: // mnemonic":"LD","operands":["(a16)","A"],"bytes":3,"cycles":16,"flagsZNHC":["-","-","-","-"]}
		case 0xee: // mnemonic":"XOR","operands":["d8"],"bytes":2,"cycles":8,"flagsZNHC":["Z","0","0","0"]}
		case 0xef: // mnemonic":"RST","operands":["28H"],"bytes":1,"cycles":16,"flagsZNHC":["-","-","-","-"]}
		case 0xf0: // mnemonic":"LDH","operands":["A","(a8)"],"bytes":2,"cycles":12,"flagsZNHC":["-","-","-","-"]}
		case 0xf1: // mnemonic":"POP","operands":["AF"],"bytes":1,"cycles":12,"flagsZNHC":["Z","N","H","C"]}
		case 0xf2: // mnemonic":"LD","operands":["A","(C)"],"bytes":2,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0xf3: // mnemonic":"DI","operands":[],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0xf5: // mnemonic":"PUSH","operands":["AF"],"bytes":1,"cycles":16,"flagsZNHC":["-","-","-","-"]}
		case 0xf6: // mnemonic":"OR","operands":["d8"],"bytes":2,"cycles":8,"flagsZNHC":["Z","0","0","0"]}
		case 0xf7: // mnemonic":"RST","operands":["30H"],"bytes":1,"cycles":16,"flagsZNHC":["-","-","-","-"]}
		case 0xf8: // mnemonic":"LD","operands":["HL","SP+r8"],"bytes":2,"cycles":12,"flagsZNHC":["0","0","H","C"]}
		case 0xf9: // mnemonic":"LD","operands":["SP","HL"],"bytes":1,"cycles":8,"flagsZNHC":["-","-","-","-"]}
		case 0xfa: // mnemonic":"LD","operands":["A","(a16)"],"bytes":3,"cycles":16,"flagsZNHC":["-","-","-","-"]}
		case 0xfb: // mnemonic":"EI","operands":[],"bytes":1,"cycles":4,"flagsZNHC":["-","-","-","-"]}
		case 0xfe: // mnemonic":"CP","operands":["d8"],"bytes":2,"cycles":8,"flagsZNHC":["Z","1","H","C"]}
		case 0xff: // mnemonic":"RST","operands":["38H"],"bytes":1,"cycles":16,"flagsZNHC":["-","-","-","-"]}
		}
	}
	
	private class Clock implements Runnable{

		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
		
		int clockspeed = 4194304;
		
		public Clock(int clockspeedHz) {
			this.clockspeed = clockspeedHz;
		}
		
		public Clock() {}
		
		public void start() {
			double delay = (1.0 / clockspeed * 100);
			executorService.scheduleAtFixedRate(this, 0, (int)delay, TimeUnit.NANOSECONDS);
		}
		
		public void stop() {
			executorService.shutdown();
		}
		
		@Override
		public void run() {
			tick();		
		}
		
	}
}
