package net.mcpandemic.core.infectedmanager;

import net.mcpandemic.core.ranks.InfectedRank;


public class RankupManager {

    public static InfectedRank getNextRank(InfectedRank rank) {
        switch(rank) {
            case A:
                return InfectedRank.B;
            case B:
                return InfectedRank.C;
            case C:
                return InfectedRank.D;
            case D:
                return InfectedRank.E;
            case E:
                return InfectedRank.F;
            case F:
                return InfectedRank.G;
            case G:
                return InfectedRank.H;
            case H:
                return InfectedRank.I;
            case I:
                return InfectedRank.J;
            case J:
                return InfectedRank.K;
            case K:
                return InfectedRank.L;
            case L:
                return InfectedRank.M;
            case M:
                return InfectedRank.N;
            case N:
                return InfectedRank.O;
            case O:
                return InfectedRank.P;
            case P:
                return InfectedRank.Q;
            case Q:
                return InfectedRank.R;
            case R:
                return InfectedRank.S;
            case S:
                return InfectedRank.T;
            case T:
                return InfectedRank.A;
            default:
                return InfectedRank.A;
        }
    }


}
