from flask import Flask, request, jsonify
import json
import pickle
import re

app = Flask(__name__)

def load_model(pickle_path):
    with open(pickle_path, 'rb') as f:
        raw_data = f.read()
        model = pickle.loads(raw_data)
    return model

#embd_model = load_model('embd_model.pickle')
#predict_model = load_model('predict_model.pickle')
#similar_model = load_model('similar_model.pickle')

@app.route('/embd', methods=["GET"])
def embd_handler():
    data = request.get_json(force=True)
    title = data['title']
    print(title)
    result = embd_model.predict(title)

    response = {
        "result": str(result)
    }
    return json.dumps(response)

@app.route('/hello', methods=["GET"])
def hello_handler():
    args = request.args
    title = args.get("title")
    print(title)

    response = {
        "result": title
    }
    print(json.dumps(response))
    return jsonify(response)


if __name__ == '__main__':
    app.run("0.0.0.0", 8000) 