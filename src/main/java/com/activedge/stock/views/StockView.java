package com.activedge.stock.views;

import com.activedge.stock.model.Stock;
import com.activedge.stock.service.StockService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route("")
public class StockView extends VerticalLayout {

    StockService stockService;

    public StockView(StockService stockService) {
        this.stockService = stockService;

        Grid<Stock> grid = new Grid<>(Stock.class, false);
        grid.addColumn(Stock::getId).setHeader("Id");
        grid.addColumn(Stock::getName).setHeader("name");
        grid.addColumn(Stock::getCurrentPrice).setHeader("Current Price");
        grid.addColumn(Stock::getUpdatedAt).setHeader("Last updated At");

        List<Stock> stocks = stockService.getStocks(0,20);
        grid.setItems(stocks);
        grid.setAllRowsVisible(true);

        add(new H1("Stock View"));
        add(grid);
    }
}
