package battleships.ships;

import battleships.Board;
import battleships.Ship;

public abstract class TemplateShip extends Ship {

    private int[][] template;

    public TemplateShip(String name, String code,int[][] template) {
        super(name, code, countSquares(template));
        this.template = template;
    }

    private static int countSquares( final int[][] template){
        int count = 0;
        for(int y =0;y< template.length;y++){
            for(int x = 0;x<template[y].length;x++){
                if (template[y][x] == 1){
                    count ++;
                }
            }
        }
        return count;
    }

    @Override
    public void rotate() {

        transpose();
        mirror();
    }

    private void transpose(){
        final int[][] nextTemplate = new int[this.getWidth()][this.getHeight()];
        for(int y = 0;y < this.getHeight();y++){
            for(int x = 0;x < this.getWidth();x++){
                nextTemplate[x][y] = this.template[y][x];
            }
        }
        this.template = nextTemplate;
    }

    private void mirror(){
        for(int y = 0;y < this.getHeight();y++){
            final int[] row = this.template[y].clone();
            for(int x = 0;x<this.getWidth();x++){
                row[x] = this.template[y][(this.getWidth()-1)-x];
            }
            this.template[y] = row;
        }
    }


    @Override
    public int getWidth() {
        return this.template[0].length;
    }

    @Override
    public int getHeight() {
        return this.template.length;
    }

    @Override
    public void addToBoard(Board board) {
        for(int y=0;y<this.getHeight();y++){
            for( int x=0;x<this.getWidth();x++){
                if ( this.template[y][x] == 1){
                    board.getSquare(x+this.x,y+this.y).setShip(this);
                }
            }
        }
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for(int y = 0;y < this.getHeight(); y++){
            for( int x = 0; x < this.getWidth(); x++){
                if (this.template[y][x] == 0){
                    builder.append("~");
                } else{
                    builder.append("O");
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
