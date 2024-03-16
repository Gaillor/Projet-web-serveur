package com.uca.core;

import com.uca.entity.*;
import com.uca.dao.TradeDAO;

public class TradeCore {
    
    public static boolean makeTrade(UserEntity u1, UserEntity u2, PokemonEntity p1, PokemonEntity p2){
        return new TradeDAO().makeTrade(u1,u2,p1,p2);
    }
}
