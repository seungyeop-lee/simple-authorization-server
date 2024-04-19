import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    port: 3000,
  },
  base: '/client/',
  build: {
    outDir: '../resources/public/client',
    emptyOutDir: true,
    sourcemap: 'inline',
    chunkSizeWarningLimit: 5000,
    rollupOptions: {
      onwarn(warning, defaultHandler) {
        if (warning.code === 'SOURCEMAP_ERROR') {
          return
        }

        defaultHandler(warning)
      },
    },
  }
})
