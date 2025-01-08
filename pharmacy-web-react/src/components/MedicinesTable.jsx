import React from 'react';


const MedicinesTable = ({ medicines }) => (

    <table style={{ marginTop: '20px', width: '80%', borderCollapse: 'collapse' }}>
        <thead>
            <tr>
                <th>Название лекарства</th>
                <th>Срок годности</th>
                <th>Начальное количество ед.</th>
                <th>Остаток на складе</th>
                <th>Стоимость</th>
            </tr>
        </thead>
        <tbody>
            {medicines.map((medicine, index) => (
                <tr key={index}>
                    <td>{medicine.drugResponse.name}</td>
                    <td>{medicine.drugResponse.expirationDate}</td>
                    <td>{medicine.initial_quantity}</td>
                    <td>{medicine.quantity}</td>
                    <td>{medicine.drugResponse.price} руб.</td>
                </tr>
            ))}
        </tbody>
    </table>
);

export default MedicinesTable;