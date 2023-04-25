import { useState } from 'react'
import axios from 'axios';
import toast, { Toaster } from 'react-hot-toast';
export default function Home() {
  const [formState, setFormState] = useState({
    amount: "",
    baseCurrency: "",
    targetCurrency: ""
  });
  const [loading, setLoading] = useState(false);
  const [result, setResult] = useState(null);
  const handleSubmit = () => {
    setResult(null)
    setLoading(true);
    axios({
      url: "http://127.0.0.1:8080/exchange",
      method: "POST",
      headers: {
                  "Access-Control-Allow-Origin": "*",
                  "Content-Type": "application/json",
              },
      data: { ...formState }
    }).then((response) => {
      setResult(response.data);
      notify("", true);
    }).catch((error) => {
      notify(error.response.data);
    }).finally(() => {
      setLoading(false);
    })
  }
  const handleChange = (e) => {
    setFormState({
      ...formState,
      [e.target.name]: e.target.value
    });
  }
  const notify = (text, isSuccess) => {
    if (isSuccess) {
      return toast.success("Success!");
    }
    return toast.error(text);
  };
  return (
    <div className='container'>
      <div className='form'>
        <h1>TECH22 - CASE</h1>
        <input className='form-control' name="amount" value={formState.amount} onChange={handleChange} placeholder='Enter amount' />
        <select className='form-control' name="baseCurrency" value={formState.baseCurrency} onChange={handleChange}>
          <option>From</option>
          <option value={"USD"}>USD</option>
          <option value={"TRY"}>TRY</option>
          <option value={"EUR"}>EUR</option>
          <option value={"JPY"}>JPY</option>
          <option value={"GBP"}>GBP</option>
        </select>
        <select className='form-control' name="targetCurrency" value={formState.targetCurrency} onChange={handleChange}>
          <option>To</option>
          <option value={"USD"}>USD</option>
          <option value={"TRY"}>TRY</option>
          <option value={"EUR"}>EUR</option>
          <option value={"JPY"}>JPY</option>
          <option value={"GBP"}>GBP</option>
        </select>
        <button disabled={loading} className='form-control-button' type='button' onClick={handleSubmit}>{loading ? "LOADING" : "CALCULATE"}</button>
        {result && <div className='results'>
          <div className='result'>{result.amount}</div>
          <div className='rate'>{formState.baseCurrency} / {formState.targetCurrency}: {result.rates[formState.targetCurrency]}</div>
        </div>}
      </div>
      <Toaster toastOptions={{
        success: {
          style: {
            background: "green",
            color: "#ffffff",
            fontFamily: "sans-serif"
          },
        },
        error: {
          style: {
            background: 'red',
            color: "#ffffff",
            fontFamily: "sans-serif"
          },
        },
      }} />
    </div>
  )
}
