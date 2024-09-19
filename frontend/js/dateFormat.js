function dateFormat(real) {
    if (real == null) return 'N/A'
    return new Date(real).toLocaleString('sr-RS')
}