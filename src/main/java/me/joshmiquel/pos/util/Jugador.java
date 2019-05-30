package me.joshmiquel.pos.util;

import me.clip.placeholderapi.PlaceholderAPI;

import me.joshmiquel.pos.Main;
import net.md_5.bungee.api.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;


public class Jugador<E> {
    Player j;
    String[] stats;
    E[] values;
    int numAtributos;
    final int TAMANIO=41;

    public Jugador(Player s) {
        j = s;
        stats = new String[TAMANIO];
        values = (E[]) new Object[TAMANIO];
        numAtributos = 0;
    }

    public void añadirAtributo(String stat) {
        if (numAtributos >= TAMANIO) {
            //sacar el mensaje de que esta lleno
        } else {
            stats[numAtributos] = stat;
            values[numAtributos] = null;
            numAtributos++;
        }
    }

    public void añadirValor(E value, int position) {//Añade al atributo escogido
        if (stats[position] != null) {
            values[position] = value;
            numAtributos++;
        }//else sacar mensaje en consola que no existe esa posicion

    }


    public void añadirAtributo(String stat, Integer pos) {
        if (numAtributos < TAMANIO) {
            if (stats[pos] != null) {
                for (int i = numAtributos++; i > pos; i--) {
                    stats[pos + 1] = stats[i];
                    values[pos + 1] = values[i];
                }
                stats[pos] = stat;
                values[pos] = null;
                numAtributos++;

            } else {
                stats[stats.length] = stat;
                values[values.length] = null;
                numAtributos++;
            }
        }//else mensajito lleno
        //
    }

    public void borrarAtributo(int pos) {
        if (stats[pos] != null) {
            for (int i = pos; i >= numAtributos; i++) {
                stats[i] = stats[i + 1];
                values[i] = values[i + 1];
            }
            numAtributos--;
        }//else mensajito no hay atributo

    }


    public Player getJ() {
        return j;
    }

    public void setJ(Player j) {
        this.j = j;
    }

    public String[] getStats() {
        return stats;
    }

    public void setStats(String[] stats) {
        this.stats = stats;
    }

    public E[] getValues() {
        return values;
    }

    public void setValues(E[] values) {
        this.values = values;
    }

    public int getNumAtributos() {
        return numAtributos;
    }

    public void setNumAtributos(int numAtributos) {
        this.numAtributos = numAtributos;
    }
}
