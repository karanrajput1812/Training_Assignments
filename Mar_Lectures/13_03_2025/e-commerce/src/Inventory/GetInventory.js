import React, { useEffect, useState } from "react";
import axios from "axios";

function GetInventory() {
    const [inventory, setInventory] = useState([]);
    const [editingItem, setEditingItem] = useState(null);
    const [updatedItem, setUpdatedItem] = useState({});

    useEffect(() => {
        axios.get("http://localhost:4000/inventory")
            .then((res) => setInventory(res.data))
            .catch((err) => console.error(err));
    }, []);

    function deleteItem(id) {
        axios.delete("http://localhost:4000/inventory/" + id)
            .then((res) => {
                console.log(`Item Deleted Successfully`, res.data);
                setInventory(inventory.filter(item => item.id !== id));
            })
            .catch((err) => {
                console.error(err);
            });
    }

    function updateItem(id) {
        const item = inventory.find(item => item.id === id);
        setEditingItem(item);
        setUpdatedItem(item);
    }

    function handleUpdateChange(e) {
        const { name, value } = e.target;
        setUpdatedItem({ ...updatedItem, [name]: value });
    }

    function saveUpdatedItem() {
        axios.put("http://localhost:4000/inventory/" + updatedItem.id, updatedItem)
            .then((res) => {
                console.log(`Item Updated Successfully`, res.data);
                setInventory(inventory.map(item => item.id === updatedItem.id ? updatedItem : item));
                setEditingItem(null);
            })
            .catch((err) => {
                console.error(err);
            });
    }

    return (
        <div className='inventory-form'>
            <h1>Inventory</h1>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Product ID</th>
                        <th>Quantity</th>
                        <th>Date</th>
                        <th>Status</th>
                        <th>Price</th>
                        <th>Discount</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    {inventory.map((item) => (
                        <tr key={item.id}>
                            <td>{item.id}</td>
                            <td>{item.product_id}</td>
                            <td>{item.quantity}</td>
                            <td>{item.date}</td>
                            <td>{item.status}</td>
                            <td>{item.price}</td>
                            <td>{item.discount}</td>
                            <td>
                                <button type="button" onClick={() => deleteItem(item.id)} className='delete-btn'>Delete</button>
                                <button type="button" onClick={() => updateItem(item.id)} className='update-btn'>Update</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            {editingItem && (
                <div className='update-form'>
                    <h2>Update Inventory Item</h2>
                    <form>
                        <label>
                            Product ID:
                            <input type="text" name="product_id" value={updatedItem.product_id} onChange={handleUpdateChange} />
                        </label>
                        <label>
                            Quantity:
                            <input type="text" name="quantity" value={updatedItem.quantity} onChange={handleUpdateChange} />
                        </label>
                        <label>
                            Vendor ID:
                            <input type="text" name="v_id" value={updatedItem.v_id} onChange={handleUpdateChange} />
                        </label>
                        <label>
                            Date:
                            <input type="text" name="date" value={updatedItem.date} onChange={handleUpdateChange} />
                        </label>
                        <label>
                            Status:
                            <input type="text" name="status" value={updatedItem.status} onChange={handleUpdateChange} />
                        </label>
                        <label>
                            Price:
                            <input type="text" name="price" value={updatedItem.price} onChange={handleUpdateChange} />
                        </label>
                        <label>
                            Discount:
                            <input type="text" name="discount" value={updatedItem.discount} onChange={handleUpdateChange} />
                        </label>
                        <button type="button" onClick={saveUpdatedItem} className='submit-btn'>Save</button>
                    </form>
                </div>
            )}
        </div>
    );
}

export default GetInventory;