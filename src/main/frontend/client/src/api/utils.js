export const post = async (url, data) => {
    const response = await fetch(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(data),
    });
    return response.json();
};

export const get = async (url) => {
  const response = await fetch(url, {
    headers: {
      'Content-Type': 'application/json',
    },
  });
  return response.json();
}

export const del = async (url) => {
  const response = await fetch(url, {
      method: 'DELETE',
      headers: {
          'Content-Type': 'application/json',
      },
  });
  return response.text();
};